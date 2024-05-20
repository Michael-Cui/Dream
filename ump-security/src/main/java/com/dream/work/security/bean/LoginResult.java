package com.dream.work.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult
{
    
    public static final int SUCCESS = 0;
    
    public static final int FAILED = 1;
    
    public static final String MSG_SUCCESS = "0";
    
    public static final String MSG_FAILED_BIND_IP_ERROR = "1";
    
    public static final String MSG_FAILED_ACCOUNT_EXPIRE = "2";
    
    public static final String MSG_FAILED_PASSWD_EXPIRE = "3";
    
    public static final String MSG_FAILED_FIRST_LOGIN = "4";
    
    public static final String MSG_FAILED = "5";
    
    public static final String MSG_ALREADY_LOGIN = "7";
    
    public static final String MSG_USER_FORBID = "8";
    
    public static final String MSG_NOT_MATCH_LOGIN_TIME = "10";

    public static final String MSG_EACH_LOGIN_ERROR = "11";
    
    private int code;
    
    private String result;
    
    private UserInfo loginInfo;
    
    private String sessioncode;

    private Long remainTime;
    
}
