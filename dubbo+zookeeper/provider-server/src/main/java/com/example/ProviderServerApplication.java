package com.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo         // 开启 Dubbo 的注解
public class ProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServerApplication.class, args);
    }

}
