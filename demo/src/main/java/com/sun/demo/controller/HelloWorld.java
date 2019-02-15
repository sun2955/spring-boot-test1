package com.sun.demo.controller;

import com.sun.demo.dto.HUSER;
import com.sun.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Value("${message.data.name}")
    private String name;

    @Value("${message.data.age}")
    private String age;

    @Value("${message.data.school}")
    private String school;

    @Value("${testStatic.data.hehe}")
    private String hehe;

    @Autowired
    public HelloService helloService;


    @GetMapping(value = "hello")
    public String Hello(){
        helloService.say();
        System.out.println("name="+name + "age="+age + "school="+school+"hehe="+hehe);
        return "hello world";
    }


    @GetMapping(value = "select")
    public HUSER hello(){
        HUSER huser = helloService.select(new Long(1));
        return huser;
    }
}
