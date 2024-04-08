package com.itheima.test;

import com.itheima.ioc.Autowired;
import com.itheima.ioc.Component;

@Component
public class A {
    @Autowired
//    @Resource
    B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public void foo() {
        System.out.println("foo");
    }
}
