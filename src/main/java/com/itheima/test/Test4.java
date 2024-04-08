package com.itheima.test;

import com.itheima.ioc.AutowiredBeanPostProcessor;
import com.itheima.ioc.BeanFactory;
import com.itheima.ioc.BeanFactoryImpl;
import com.itheima.ioc.ResourceBeanPostProcessor;
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
