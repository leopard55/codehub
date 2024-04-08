package com.itheima.day02.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

public class TestBeanFactory2 {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 到类路径下扫描这些加了@Component注解的类，把它们变成BeanDefinition交给beanfactory管理
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.scan("com.itheima.day02.beanfactory");

        // @ComponentScan("com.itheima.day02.beanfactory")
        System.out.println(factory.getBean(MyBean.class));
        System.out.println(factory.getBean(MyBean2.class));
        System.out.println(factory.getBean(MyBean3.class));

    }

    @Component
    static class MyBean {

    }

    @Component
    static class MyBean2 {

    }

    @Component
    static class MyBean3 {

    }
}
