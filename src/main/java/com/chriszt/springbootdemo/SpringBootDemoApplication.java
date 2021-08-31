package com.chriszt.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDemoApplication {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        System.out.println("Hello World");
        return "Hello World";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        System.out.println("Hello " + name);
        return "Hello " + name;
    }

    @RequestMapping("/flink")
    @ResponseBody
    public String flink() {
        String[] words = new String[] {
                "com.chriszt.flink.stream.helloworld.WordCount",
                "com.chriszt.flink.stream.helloworld.WordCount"
        };

        new WordCount().wordCount(words);
        return "OK";
    }

    @RequestMapping("/rule")
    @ResponseBody
    public String rule() {
        String filePath = getClass().getResource("/userTab.csv").getPath();
        new RuleTask().task(filePath);
        return "OK";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
