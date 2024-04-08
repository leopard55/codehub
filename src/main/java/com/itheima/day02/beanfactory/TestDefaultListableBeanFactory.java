package com.itheima.day02.beanfactory;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class TestDefaultListableBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 管理某个bean  建造器, 创建bean定义对象
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(MyBean.class)
                .setScope("singleton") // 单例
                .setInitMethodName("init") // 初始化方法是init
                .getBeanDefinition();
        // 注册了bean定义对象
        factory.registerBeanDefinition("myBean", beanDefinition);

        for (String name : factory.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        MyBean bean = factory.getBean(MyBean.class);
        System.out.println(bean);

        bean = factory.getBean(MyBean.class);
        System.out.println(bean);

    }

    static class MyBean { // 单例singleton 多例prototype @PostConstruct

        public MyBean() {
            System.out.println("构造方法被调用");
        }

        public void init() {
            System.out.println("初始化方法被调用");
        }
    }
}
