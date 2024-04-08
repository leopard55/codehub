package com.itheima.test;

import com.itheima.ioc.AutowiredBeanPostProcessor;
import com.itheima.ioc.BeanFactory;
import com.itheima.ioc.BeanFactoryImpl;
import com.itheima.ioc.ResourceBeanPostProcessor;
import com.itheima.test.sub.C;

public class Test1 {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactoryImpl(MyConfig.class,
                new ResourceBeanPostProcessor(), new AutowiredBeanPostProcessor());


        A a = beanFactory.getBean(A.class);
        B b = beanFactory.getBean(B.class);
        C c = beanFactory.getBean(C.class);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println("---------------");
        System.out.println(a.getB());
    }
}
