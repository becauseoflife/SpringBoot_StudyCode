package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 程序的主入口
// @SpringBootApplication: 标注这个类是一个 SpringBoot 的应用。
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        // 该方法返回一个 configurableApplicationContext 对象
        // 参数一:应用入口的类 参数二:命令行参数
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
