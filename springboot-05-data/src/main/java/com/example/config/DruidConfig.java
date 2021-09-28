package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")      // 与 application.yml 绑定
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // 后台监控 (访问 /druid/*  进入后台监控界面)
    // 因为 SpringBoot 内置了 Servlet 容器，所以没有 web.xml. 替代方法：ServletRegistrationBean
    // 相当于 web.xml 配置这个 Servlet: ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 后台需要有人登陆，就是账号和密码
        HashMap<String, String> initParameters = new HashMap<>();
        // 增加配置
        initParameters.put("loginUsername", "admin");   // 登陆的 key 是固定的 loginUsername, loginPassword
        initParameters.put("loginPassword","123456");

        // 允许谁访问
        initParameters.put("allow", "");   // "localhost" ==> 允许本机访问

        // 禁止谁访问 initParameters.put("mianbao", "192.198.10.10");

        bean.setInitParameters(initParameters);   // 设置初始化参数

        return bean;
    }

    // filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        // 可以过滤哪些请求
        HashMap<String, String> initParameters = new HashMap<>();

        // 这些东西不用统计
        initParameters.put("exclusions", "*.js, *.css, /druid/*");    // 点到 WebStatFilter 里，查看可配置选项

        bean.setInitParameters(initParameters);
        return bean;
    }
}
