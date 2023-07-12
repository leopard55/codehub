package com.itheima;

public interface BeanFactory {

    /**
     * 将某个类型注册到 spring 容器
     * @param clz 类型
     */
    void register(Class<?> clz);

    /**
     * 获取 bean 实例
     * @param clz 类型
     * @return 对象实例
     * @param <T> 泛型
     */
    <T> T getBean(Class<T> clz);

    /**
     * 依赖注入方法
     * @param obj 被依赖注入的对象
     */
    void autowired(Object obj);
}
