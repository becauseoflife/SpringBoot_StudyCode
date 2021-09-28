package com.example.swagger.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(){
        StringUtils.isEmpty("");
        return "hello";
    }
}
