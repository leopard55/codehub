package com.itheima.ioc;

import java.lang.reflect.Field;

public class AutowiredBeanPostProcessor implements BeanPostProcessor{
    @Override
    public void enhance(BeanFactory beanFactory, Object a) {
//        if (a instanceof HeimaProxy proxy) {
//            a = proxy.target();
//        }
        // a.getClass() 得到的是代理类型
        // a.getClass().getSuperclass() 得到原始类型
        for (Field field : a.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> type = field.getType();
                Object b = beanFactory.getBean(type);
                field.setAccessible(true);
                try {
                    field.set(a, b); // 给代理对象继承过来的属性b进行了依赖注入
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
