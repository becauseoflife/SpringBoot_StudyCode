package com.example.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
// @EnableSwagger2     // 开启 Swagger2  3.0之前的版本
@EnableOpenApi      // 3.0 版本
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String env;

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("分组1");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("分组2");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("分组3");
    }

    // 配置了 swagger 的 Docket 的 bean 实例
    @Bean
    public Docket docket(Environment environment){
        // 设置要显示的 Swagger 环境
        Profiles profiles = Profiles.of("dev", "test");
        // 获取项目的环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .groupName("面包")
                .select()
                // RequestHandlerSelectors 配置要扫描的接口方式
                // basePackage() 指定要扫描的包
                // any() 扫描全部
                // none() 都不扫描
                // withClassAnnotation()  扫描类上的注解，参数是一个注解的返回对象
                // withMethodAnnotation()  扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                // paths(): 过滤路径
                .paths(PathSelectors.ant("/example/**"))
                .build();
    }

    // 配置 Swagger 信息 apiInfo
    private ApiInfo apiInfo(){

        // 作者信息
        Contact contact = new Contact("面包", "http://localhost", "843818747@qq.com");

        return new ApiInfo(
                "面包 API 文档",
                "这个是描述",
                "1.0",
                "http://localhost",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

}
