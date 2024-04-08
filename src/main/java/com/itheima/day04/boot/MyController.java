package com.itheima.day04.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello, " + name;
    }

    public String abc() {
        return "";
    }
}
