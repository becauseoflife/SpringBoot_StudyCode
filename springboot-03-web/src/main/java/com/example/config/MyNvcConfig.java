package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

// 扩展 Spring MVC

@Configuration
public class MyNvcConfig implements WebMvcConfigurer {

    // ContentNegotiatingViewResolver ——> ViewResolver 实现了视图解析器接口，我们就可以把他看做视图解析器

    // 放入到 Spring 容器中
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    // 自定义一个视图解析器 MyViewResolver
    public static class MyViewResolver implements ViewResolver {

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

}
