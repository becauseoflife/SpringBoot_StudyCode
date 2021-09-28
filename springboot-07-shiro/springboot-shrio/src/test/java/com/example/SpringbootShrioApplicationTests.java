package com.example;

import com.example.pojo.User;
import com.example.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShrioApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        User user = userService.queryUserByName("pan");
        System.out.println(user);
    }

}
