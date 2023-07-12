package com.test;

import com.itheima.AutowiredBeanPostProcessor;
import com.itheima.BeanFactory;
import com.itheima.BeanFactoryImpl;
import com.itheima.ResourceBeanPostProcessor;
import com.itheima.aop.ProxyBeanPostProcessor;

public class Test4 {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactoryImpl(MyConfig.class,
                new ProxyBeanPostProcessor(MyConfig.class),
                new AutowiredBeanPostProcessor(),
                new ResourceBeanPostProcessor());

        A a = beanFactory.getBean(A.class);
//        a.foo();

//        B b = beanFactory.getBean(B.class);
        B b = a.getB();
        System.out.println(b.getClass());
        b.bar();
    }
}
