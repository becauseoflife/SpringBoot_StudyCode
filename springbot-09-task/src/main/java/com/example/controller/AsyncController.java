package com.example.controller;

import com.example.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    public String hello(){
        asyncService.hello();
        return "OK";
    }
}
