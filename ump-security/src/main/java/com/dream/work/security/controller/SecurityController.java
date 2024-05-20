package com.dream.work.security.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dream.work.security.bean.LoginInfo;
import com.dream.work.security.bean.LoginResult;
import com.dream.work.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/sm/v1")
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @PostMapping("login")
    public JSONObject doLogin(@RequestBody LoginInfo loginInfo) {
        LoginResult result = securityService.doLogin(loginInfo);

        return JSONObject.from(result);
    }
}
