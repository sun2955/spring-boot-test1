package com.sun.demo.controller.api;

import com.sun.demo.base.config.shiro.MyShiroRealm;
import com.sun.demo.service.HelloService;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/api/shiro")
public class ShiroTest {

    private Logger log = Logger.getLogger(ShiroTest.class);

    @GetMapping(value = "hello")
    @RequiresRoles("admin")
    public String Hello(){
        log.info("进入项目管理首页...");
        return "index";
    }

    @GetMapping(value = "hello2")
    public String Hello2(){
        log.info("进入项目管理首页...");
        return "index";
    }
}
