package com.dream.work.security.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements UserDetails
{
    @ExcelIgnore
    private String userID;
    
    @ExcelProperty(index = 0,value = "用户名")
    private String userName;
    
    @ExcelIgnore
    private String userPasswd;

    @ExcelIgnore
    private String salt;

    @ExcelIgnore
    private String userRole;
    
    @ExcelProperty(index = 1,value = "电话号码")
    private String userPhone;
    
    @ExcelProperty(index = 2,value = "邮箱")
    private String userMailbox;
    
    @ExcelProperty(index = 10,value = "用户状态")
    private String userSafeState;
    
    @ExcelProperty(index = 9,value = "IP地址")
    private String userSafeAddress;
    
    @ExcelIgnore
    private String userLoginCount;
    
    @ExcelProperty(index = 6,value = "最后登录时间")
    private String userLoginTime;
    
    @ExcelIgnore
    private String userAccountTime;
    
    @ExcelIgnore
    private String userExpiryDateCount;
    
    @ExcelProperty(index = 7,value = "账户有效期")
    private String userExpiryDate;
    
    @ExcelIgnore
    private String userPassExpiryDateCount;
    
    @ExcelProperty(index = 8,value = "密码有效期")
    private String userPassExpiryDate;
    
    @ExcelIgnore
    private String userSessionTime;
    
    @ExcelIgnore
    private String userSort;
    
    @ExcelProperty(index = 5,value = "用户描述")
    private String userDetails;

    @ExcelProperty(index = 3,value = "姓名")
    private String name;

    @ExcelProperty(index = 4,value = "单位")
    private String  company;

    @ExcelIgnore
    private List<Integer> userGroupIdList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userPasswd;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
