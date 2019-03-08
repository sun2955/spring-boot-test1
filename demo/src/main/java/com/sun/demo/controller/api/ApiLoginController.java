package com.sun.demo.controller.api;

import com.sun.demo.base.WebRespone;
import com.sun.demo.base.utils.JWTUtil;
import com.sun.demo.dto.HUSER;
import com.sun.demo.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Api(value = "测试接口2", tags = "测试接口登录", description = "Hello World")
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
public class ApiLoginController {

    private static final Logger _logger = LoggerFactory.getLogger(ApiLoginController.class);
    @Autowired
    public HelloService helloService;

    @ApiOperation(value = "登录", notes = "登录", produces = "application/json")
    @GetMapping(value = "/login")
    public WebRespone login(){
        _logger.info("用户请求登录获取Token");
        HUSER huser = helloService.select(new Long(1));
        String token = "";
        if(huser !=null){
            String username = huser.getUserName();
            String password =  huser.getPassword();

             token = JWTUtil.sign(username);
            _logger.info("用户登录Token="+token);

        }
        return WebRespone.success("OJBK", token);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录", produces = "application/json")
    @GetMapping(value = "/logout/{username}")
    public WebRespone logout(@RequestHeader(value = "Authorization") String authorization,@PathVariable String username){
        username = "sun2955";

        try {
            JWTUtil.verify(authorization,username);
        } catch (Exception e) {
            return WebRespone.error(e.getMessage());
        }

        return WebRespone.success("OJBK", "验证成功,退出登录");
    }
}
