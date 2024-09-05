package com.dream.work.auth.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSONObject;
import com.dream.work.auth.annotation.IgnoreAuth;
import com.dream.work.auth.entity.FullUserInfo;
import com.dream.work.auth.entity.UserInfo;
import com.dream.work.auth.entity.UserVo;
import com.dream.work.auth.service.ApiUserService;
import com.dream.work.auth.service.TokenService;
import com.dream.work.auth.service.WeixinMaService;
import com.dream.work.auth.utils.CharUtil;
import com.dream.work.auth.utils.validation.AbstractAssert;
import com.mysql.cj.util.StringUtils;
//import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @gitee https://gitee.com/fuyang_lipengjun/platform
 * @date 2017-03-23 15:31
 */
@Slf4j
@Api(tags = "登录授权-ApiAuthController")
@RestController
@RequestMapping("/dreamwork/api/auth")
public class ApiAuthController extends ApiBaseAction {
    @Autowired
    private ApiUserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private WeixinMaService weixinMaService;

    private static final String DEFAULT_AVATAR = "https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0";
    private static final String DEFAULT_USER_PREFIX = "微信用户";

    /**
     * 登录
     */
//    @IgnoreAuth
//    @PostMapping("login")
//    @ApiOperation(value = "登录接口")
//    public R login(String mobile, String password) {
//        AbstractAssert.isBlank(mobile, "手机号不能为空");
//        AbstractAssert.isBlank(password, "密码不能为空");
//
//        //用户登录
//        long userId = userService.login(mobile, password);
//
//        //生成token
//        Map<String, Object> map = tokenService.createToken(userId);
//
//        return R.ok(map);
//    }

    /**
     * 微信登录
     */
    @ApiOperation(value = "微信登录")
    @IgnoreAuth
    @PostMapping("login_by_weixin")
    public Object loginByWeixin() {
        JSONObject jsonParam = this.getJsonRequest();
        FullUserInfo fullUserInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);
        }
        if (null == fullUserInfo) {
            return toResponseFail("登录失败");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        UserInfo userInfo = fullUserInfo.getUserInfo();

        try {
            WxMaJscode2SessionResult session = weixinMaService.getUserService().getSessionInfo(code);
            // 用户信息校验
            log.info("》》》微信返回sessionData：" + session.toString());

            if (!weixinMaService.getUserService().checkUserInfo(session.getSessionKey(), fullUserInfo.getRawData(), fullUserInfo.getSignature())) {
                log.error("登录失败：数据签名验证失败");
                return toResponseFail("登录失败");
            }

            // 解密用户信息
            WxMaUserInfo wxMpUser = weixinMaService.getUserService().getUserInfo(session.getSessionKey(), fullUserInfo.getEncryptedData(), fullUserInfo.getIv());
            log.info("》》》解密用户信息：" + wxMpUser.toString());

            Date nowTime = new Date();
            UserVo userVo = userService.queryByOpenId(session.getOpenid());
            if (null == userVo) {
                userVo = new UserVo();
                String name = DEFAULT_USER_PREFIX + CharUtil.getRandomString(12);
                userVo.setUsername(name);
                userVo.setPassword(session.getOpenid());
                userVo.setRegisterTime(nowTime);
                userVo.setRegisterIp(this.getClientIp());
                userVo.setLastLoginIp(userVo.getRegisterIp());
                userVo.setLastLoginTime(nowTime);
                userVo.setWeixinOpenid(session.getOpenid());
                userVo.setAvatar(DEFAULT_AVATAR);
                userVo.setNickname(name);
                userService.save(userVo);
            } else {
                userVo.setLastLoginIp(this.getClientIp());
                userVo.setLastLoginTime(nowTime);
                userService.update(userVo);
            }

            Map<String, Object> tokenMap = tokenService.createToken(userVo.getUserId());
            String token = MapUtils.getString(tokenMap, "token");

            if (null == userInfo || StringUtils.isNullOrEmpty(token)) {
                return toResponseFail("登录失败");
            }

            resultObj.put("token", token);
            resultObj.put("userInfo", userInfo);
            resultObj.put("userId", userVo.getUserId());
            return toResponseSuccess(resultObj);
        } catch (WxErrorException e) {
            log.error("登录失败：" + e.getMessage());
            return toResponseFail("登录失败");
        }
    }

    @ApiOperation(value = "更新用户头像/昵称")
    @IgnoreAuth
    @PostMapping("update_user_info")
    public Object updateUserInfo() {
        JSONObject jsonParam = this.getJsonRequest();

        UserVo userInfo = null;
        if (null != jsonParam.get("userInfo")) {
            userInfo = jsonParam.getObject("userInfo", UserVo.class);
        }
        if (null == userInfo) {
            return toResponseFail("Invalid parameters.");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();

        try {
            UserVo userVo = userService.queryObject(userInfo.getUserId());
            if (null == userVo) {
                return toResponseFail("Invalid parameters(Cannot find user through openid).");
            } else {
                if(userInfo.getNickname() != null) {
                    userVo.setNickname(userInfo.getNickname());
                }

                if(userInfo.getAvatar() != null) {
                    userVo.setAvatar(userInfo.getAvatar());
                }

                userService.update(userVo);
            }

            resultObj.put("userInfo", userVo);
            resultObj.put("userId", userVo.getUserId());
            return toResponseSuccess(resultObj);
        } catch (Exception e) {
            log.error("Update user info failed: " + e.getMessage());
            return toResponseFail("Update user info failed");
        }
    }

    /**
     * code静默登录
     *
     * @param code code
     * @return RestResponse
     */
    @IgnoreAuth
    @GetMapping("/{code}")
    @ApiOperation(value = "微信静默登录", notes = "使用code静默登录")
    @ApiImplicitParam(required = true, paramType = "path", name = "code", value = "code", example = "oxaA11ulr9134oBL9Xscon5at_Gc", dataType = "string")
    public Object loginByCode(@PathVariable String code) {
        try {
            WxMaJscode2SessionResult session = weixinMaService.getUserService().getSessionInfo(code);

            String openid = session.getOpenid();

            Date nowTime = new Date();
            UserVo userVo = userService.queryByOpenId(openid);
            if (null == userVo) {
                userVo = new UserVo();
                String name = DEFAULT_USER_PREFIX + CharUtil.getRandomString(12);
                userVo.setUsername(name);
                userVo.setPassword(openid);
                userVo.setRegisterTime(nowTime);
                userVo.setRegisterIp(this.getClientIp());
                userVo.setLastLoginIp(userVo.getRegisterIp());
                userVo.setLastLoginTime(nowTime);
                userVo.setWeixinOpenid(openid);
                userVo.setAvatar(DEFAULT_AVATAR);
                userVo.setNickname(name);
                userService.save(userVo);
            } else {
                userVo.setLastLoginIp(this.getClientIp());
                userVo.setLastLoginTime(nowTime);
                userService.update(userVo);
            }

            Map<String, Object> tokenMap = tokenService.createToken(userVo.getUserId());
            String token = MapUtils.getString(tokenMap, "token");

            if (StringUtils.isNullOrEmpty(token)) {
                return toResponseFail("登录失败");
            }

            Map<String, Object> resultObj = new HashMap<>();
            resultObj.put("token", token);
            resultObj.put("userInfo", userVo);
            resultObj.put("userId", userVo.getUserId());
            return toResponseSuccess(resultObj);
        } catch (WxErrorException e) {
            log.error("登录失败", e);
            return toResponseFail("登录失败：" + e.getError().getErrorMsg());
        }
    }
}
