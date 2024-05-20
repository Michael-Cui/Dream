package com.dream.work.security.service.impl;

import com.dream.work.security.bean.LoginInfo;
import com.dream.work.security.bean.LoginResult;
import com.dream.work.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    public LoginResult doLogin(LoginInfo userlogininfo)
    {
        // TODO
        LoginResult loginResult = new LoginResult();
        return loginResult;
    }
}
