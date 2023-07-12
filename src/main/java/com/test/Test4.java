package com.test;

import com.itheima.AutowiredBeanPostProcessor;
import com.itheima.BeanFactory;
import com.itheima.BeanFactoryImpl;
import com.itheima.ResourceBeanPostProcessor;
import com.itheima.aop.ProxyBeanPostProcessor;

public class Test4 {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactoryImpl(MyConfig.class,
                new AutowiredBeanPostProcessor(),
                new ResourceBeanPostProcessor(),
                new ProxyBeanPostProcessor(MyConfig.class));

        A a = beanFactory.getBean(A.class);
        System.out.println(a.getClass());
        a.foo();

//        B b = beanFactory.getBean(B.class);
        B b = a.getB();
        b.bar();
    }
}
