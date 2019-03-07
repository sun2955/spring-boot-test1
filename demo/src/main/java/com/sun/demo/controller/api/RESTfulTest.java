package com.sun.demo.controller.api;

import com.sun.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class RESTfulTest {


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String Hello(){
        return "初始化...";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String postUser(@PathVariable String name) {
        return "新增成功";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return "查询成功";
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable String id) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        return "更新成功";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        return "删除成功";
    }
}
