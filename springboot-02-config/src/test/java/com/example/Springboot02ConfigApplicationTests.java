package com.example;

import com.example.pojo.Dog;
import com.example.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class Springboot02ConfigApplicationTests {


    @Autowired
    private Person person;

    @Autowired
    private Dog dog;

    @Test
    void contextLoads() {

    }



}
