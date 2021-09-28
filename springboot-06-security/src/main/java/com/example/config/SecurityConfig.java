package com.example.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.provider.MD5;

// Aop : 拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 链式编程
    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问， 功能页只有对应权限的人才能访问
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没有权限默认会到登录页, 需要开启登录的界面 默认会调到 /login 界面
        // loginPage("/toLogin") ： 定制登录页面
        http.formLogin().loginPage("/toLogin")
                .usernameParameter("username")      // 前端表单的 name 属性值
                .passwordParameter("password")      // 前端表单的 name 属性值
                .loginProcessingUrl("/login");      // 提交表单的地址

        http.csrf().disable();  // 关闭 csrf 功能

        // 注销。开启了注销功能
        http.logout().logoutSuccessUrl("/");    // 跳转到首页

        // 记住我, cookie: 默认保存两周
        http.rememberMe()
                .rememberMeParameter("remember");       // 自定义接收前端的参数
    }

    // 认证   SpringBoot 2.1.x 可以直接使用
    // 密码编码错误： PasswordEncoder  在 Spring Security 中新增了许多加密的方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.jdbcAuthentication() 从数据库中
        // 正常这些数据是从数据库中读取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())   // 在内存中读
                .withUser("mianbao").password(new BCryptPasswordEncoder().encode("123")).roles("vip1", "vip2")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip3");
    }
}
