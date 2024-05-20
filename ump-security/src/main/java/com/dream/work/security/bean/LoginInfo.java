package com.dream.work.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo
{
    private String username;
    
    private String password;
    
    private String isEncypted = "false";
    
    private String vcode;
}
