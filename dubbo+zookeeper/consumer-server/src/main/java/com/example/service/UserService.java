package com.example.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 获取 provider-server 提供的票, 要去注册中心拿到服务
    @DubboReference  // 引用-> 1.pom坐标 2.定义路径相同的接口名 com.example.service.TicketService
    TicketService ticketService;

    public void buyTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到==>" + ticket);
    }

}
