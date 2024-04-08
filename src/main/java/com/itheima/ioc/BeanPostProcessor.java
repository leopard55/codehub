package com.itheima.ioc;

public interface BeanPostProcessor {

    /**
     * 增强 bean 的功能
     * @param beanFactory 容器
     * @param bean 待增强的 bean
     */
    void enhance(BeanFactory beanFactory, Object bean);

}
