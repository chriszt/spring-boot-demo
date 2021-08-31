package com.chriszt.springbootdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Value("${user1.id}")
    private int id;

    @Value("${user1.clazz}")
    private String clazz;

    @Value("${user1.name}")
    private String name;

    @Value("${user1.age}")
    private int age;

    @RequestMapping("/user1")
    @ResponseBody
    public String user1() {
        return id + ", " + clazz + ", " + name + ", " + age;
    }

}
