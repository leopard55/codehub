package com.itheima;

import java.lang.reflect.Field;

public class ResourceBeanPostProcessor implements BeanPostProcessor{
    @Override
    public void enhance(BeanFactory beanFactory, Object a) {
        for (Field field : a.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Resource.class)) {
                Class<?> type = field.getType();
                Object b = beanFactory.getBean(type);
                field.setAccessible(true);
                try {
                    field.set(a, b);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
