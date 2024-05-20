package com.dream.work.security.service;

import com.dream.work.security.bean.LoginInfo;
import com.dream.work.security.bean.LoginResult;

public interface SecurityService {

    LoginResult doLogin(LoginInfo loginInfo);
}
