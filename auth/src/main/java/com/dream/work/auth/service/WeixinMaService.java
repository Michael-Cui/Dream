package com.dream.work.auth.service;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.dream.work.auth.utils.ResourceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author pengjun
 */
@Service
public class WeixinMaService extends WxMaServiceImpl {

    @PostConstruct
    public void init() {
        final WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        //TODO implementations should be done
        // 设置微信公众号的appid
        config.setAppid("wx256a11207f6b7e54");
        config.setSecret("64b568570056837de12f901f62346d9e");
//        config.setAppid(ResourceUtil.getConfigByName("wx.appId"));
//        // 设置微信公众号的app corpSecret
//        config.setSecret(ResourceUtil.getConfigByName("wx.secret"));
//        // 设置微信公众号的token
//        config.setToken(ResourceUtil.getConfigByName("wx.token"));
//        // 设置消息加解密密钥
//        config.setAesKey(ResourceUtil.getConfigByName("wx.aesKey"));
        super.setWxMaConfig(config);
    }
}
