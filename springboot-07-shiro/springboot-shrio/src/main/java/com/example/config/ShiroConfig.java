package com.example.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean  第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);

        // 添加shiro的内置过滤器
        /*
         * anon: 无需认证就能访问
         * authc: 必须认证的才能访问
         * user: 必须拥有 记住我 功能才能用
         * perms: 拥有对某个资源的权限才能访问
         * role: 拥有某个角色权限才能访问
         * */
        // 拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //filterMap.put("/user/add", "authc");
        //filterMap.put("/user/update", "authc");

        // 授权,正常的情况下，没有授权会跳转到授权的界面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");

        filterMap.put("/user/*", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置登录的请求
        filterFactoryBean.setLoginUrl("/toLogin");
        // 设置未授权界面
        filterFactoryBean.setUnauthorizedUrl("/noauth");

        return filterFactoryBean;
    }

    // DefaultWebSecurityManager  第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建 realm 对象，需要自定义类  第一步
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    // 整合 ShiroDialect ： 用来整合 shiro thymeleaf
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
