package com.leopard.controller;

import com.leopard.log.MyLog;
import com.leopard.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/7/1 11:43
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    //HelloService在我们自定义的starter中已经完成了自动配置，所以此处可以直接注入
    @Autowired
    private HelloService helloService;

    @MyLog(desc = "sayHello方法") //日志记录注解
    @GetMapping("/say")
    public String sayHello(){
        return helloService.sayHello();
    }
}
