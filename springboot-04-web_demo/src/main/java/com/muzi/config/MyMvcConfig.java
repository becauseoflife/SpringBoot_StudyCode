package com.muzi.config;

import com.muzi.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 首页跳转实现
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");

        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 注入Bean 自定义国际化组件
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login",
                "/js/**",      // js静态资源
                "/css/**",     // css静态资源
                "/img/**"      // img静态资源
                );
    }
}
