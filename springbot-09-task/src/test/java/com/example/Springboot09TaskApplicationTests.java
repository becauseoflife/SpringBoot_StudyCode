package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        // 一个简单的邮件发送
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("面包你好");
        message.setText("Java系列课程");

        message.setTo("1752196851@qq.com");
        message.setFrom("1752196851@qq.com");

        javaMailSender.send(message);
    }

    @Test
    void contextLoads2() throws MessagingException {
        // 一个复杂的邮件发送
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("面包你好");
        helper.setText("<h2 style='color:blue'>面包你好</h2>", true);

        // 附件
        helper.addAttachment("1.jpg", new File("D:\\Desktop\\面包\\图片\\test11.png"));

        helper.setTo("1752196851@qq.com");
        helper.setFrom("1752196851@qq.com");

        javaMailSender.send(mimeMessage);
    }
}
