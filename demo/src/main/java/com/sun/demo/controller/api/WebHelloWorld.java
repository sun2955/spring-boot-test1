package com.sun.demo.controller.api;

import com.sun.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/h5")
public class WebHelloWorld {

    @Autowired
    public HelloService helloService;


    @GetMapping(value = "hello")
    public String Hello(){
        return "index";
    }
}
