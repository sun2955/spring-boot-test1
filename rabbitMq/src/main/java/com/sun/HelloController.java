package com.sun;

import com.sun.sender.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :szy
 * @title: HelloController
 * @projectName demo
 * @description: TODO
 * @date 2020/7/29-20:21
 */
@RestController
public class HelloController {

    @Autowired
    private HelloSender helloSender;

    @GetMapping("/login/{name}")
    public String list(@PathVariable("name") String name) {

        System.out.println("---------------------------send----start------------------");
        helloSender.send(name);
        System.out.println("---------------------------send-----end-----------------");

        System.out.println("---------------------------广播模式----start------------------");
        helloSender.broadcast(name);
        System.out.println("---------------------------广播模式-----end-----------------");




        System.out.println("---------------------------定向模式----start------------------");
        helloSender.direct(name);
        System.out.println("---------------------------定向模式-----end-----------------");



        return "login";
    }
}
