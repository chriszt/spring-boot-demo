package com.chriszt.springbootdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix = "user2")
public class MyController2 {

    private int id;

    private String clazz;

    private String name;

    private int age;

    public void setId(int id) {
        this.id = id;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @RequestMapping("/user2")
    @ResponseBody
    public String user2() {
        return JSON.toJSONString(new User(id, clazz, name, age), SerializerFeature.PrettyFormat);
    }

}
