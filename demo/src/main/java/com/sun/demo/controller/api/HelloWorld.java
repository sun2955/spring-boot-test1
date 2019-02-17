package com.sun.demo.controller.api;

import com.sun.demo.dto.HUSER;
import com.sun.demo.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试接口1", tags = "测试接口", description = "Hello World")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求正常完成"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
//        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
//        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
//        @ApiResponse(code = 404, message = "服务器找不到给定的资源，文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@RestController
@RequestMapping(value="/api")
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

    @ApiOperation(value = "hello", notes = "hello", produces = "application/json")
    @GetMapping(value = "hello")
    public String Hello(){
        helloService.say();
        System.out.println("name="+name + "age="+age + "school="+school+"hehe="+hehe);
        return "hello world";
    }

    @ApiOperation(value = "检查用户信息", notes = "检查用户信息", produces = "application/json")
    @GetMapping(value = "select")
    public HUSER hello(){
        HUSER huser = helloService.select(new Long(1));
        return huser;
    }
}
