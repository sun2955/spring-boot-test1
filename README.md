# spring-boot-test1
spring-boot的学习
本项目可能没有将web端和接口端分类,都写在一起了,不过在目录上分别了一下,
spring-boot-test1\demo\src\main\java\com\sun\demo\controller\web  提供后台的controller
spring-boot-test1\demo\src\main\java\com\sun\demo\controller\api  提供接口的controller
因为这是个人springboot的学习项目,接入了各种可以接入的模块,所以下面记录了各种模块的测试方法

1.数据库连接测试,                    测试 类文件   :  DemoApplicationTests

2.接入腾讯云短信平台 发送短信验证码  ,测试 类文件  :  QcloudSmsTest

3.接入rabbitmq,                    测试类文件  : RabbitMQApplicationTests

4.接入redis 读取存储的类的系列化,    测试类文件 : RedisApplicationTests

5.接入swagger2 接口文档化工具 ,注意,因为加入包路径,只会翻译某个包下面的接口, 测试路径 : http://localhost:8000/demo/swagger-ui.html

6.接入thymeleaf模板引擎,类文件目录   spring-boot-test1\demo\src\main\java\com\sun\demo\controller\web\IndexController.java
注意在使用thymeleaf 的时候,html文件要放到..\resources\templates 目录下.
测试地址不拦截地址 : http://localhost:8000/demo/api/hello 拦截地址: http://localhost:8000/demo/index.html

7.假如日志文件输出 测试地址 : \spring-boot-test1\demo\applog

8.采用 springmvc restful 接口风格.  测试方式:采用postman,改变提交类型进行测试 ,测试地址 http://localhost:8000/demo/api/user/1
