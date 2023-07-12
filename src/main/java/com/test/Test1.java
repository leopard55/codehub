package com.test;

import com.itheima.BeanFactory;
import com.itheima.BeanFactoryImpl;

public class Test1 {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactoryImpl();
        beanFactory.register(A.class);
        beanFactory.register(B.class);

        A a = beanFactory.getBean(A.class);
        B b = beanFactory.getBean(B.class);
        System.out.println(a);
        System.out.println(b);

        beanFactory.autowired(a);
        System.out.println(a.getB());
    }
}
