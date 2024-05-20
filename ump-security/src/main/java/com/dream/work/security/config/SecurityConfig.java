package com.dream.work.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .antMatchers("/my-login.html").permitAll() // loginPage 页面不需要身份认证 否则会无限重定向
                        .antMatchers("/hello/test").permitAll()
                        //.requestMatchers("/my-login.html").permitAll() // loginPage 页面不需要身份认证 否则会无限重定向
                        .anyRequest().authenticated() // 其他请求都需要用户认证后访问
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/my-login.html") // 自定义登录页面路径
                        .usernameParameter("username") // 定义从Form中获取用户名的key 与html中的form参数匹配
                        .passwordParameter("password") // 定义从Form中获取密码的key 与html中的form参数匹配
                        .loginProcessingUrl("/my/login") // 认证发起的URL，访问该URL则认证凭证 这样要与HTML中form的提交地址一致
                )
                // 禁用httpBasic
                .httpBasic((httpBasic) -> httpBasic.disable())
                // 不启用csrf
                // 如果启用csrf认证 则自定义的POST /my/login方法也会被 CsrfFilter 拦截.
                // 需要在请求参数中携带_csrf参数 或者 请求头中增加X-CSRF-TOKEN 否则csrf校验不过 又会跳转到loginPage页面
                .csrf((csrf) -> csrf.disable());
        return http.build();
    }

}
