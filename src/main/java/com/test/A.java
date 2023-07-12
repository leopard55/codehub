package com.test;

import com.itheima.Autowired;
import com.itheima.Component;
import com.itheima.Resource;

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

    public void bar() {
        System.out.println("bar");
    }
}
