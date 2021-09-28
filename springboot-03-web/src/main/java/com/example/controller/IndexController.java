package com.example.controller;

import com.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model, HttpSession session){
        model.addAttribute("msg", "hello Thymeleaf");
        model.addAttribute("umsg", "<h1>hello Thymeleaf</h1>");

        User user = new User("mianbao", 18, "男");
        session.setAttribute("user", user);

        model.addAttribute("users", Arrays.asList("面包", "Java", "Thymeleaf"));

        return "test";
    }

}
