package com.test;

import com.itheima.Autowired;

public class A {
    @Autowired
    B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
