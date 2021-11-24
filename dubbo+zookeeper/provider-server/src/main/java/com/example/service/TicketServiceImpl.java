package com.example.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

// Zookeeper 服务注册与发现

@DubboService   // 可以被扫描到，在项目启动就自动注册到注册中心
@Component      // 使用了 Dubbo，尽量不使用 @Service 注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "面包";
    }
}
