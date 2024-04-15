package com.leopard.service;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/7/1 11:01
 */
public class HelloService {
    private String name;
    private String address;

    public HelloService(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String sayHello(){
        return "你好！我的名字叫 " + name + "，我来自 " + address;
    }
}
