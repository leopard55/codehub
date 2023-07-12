package com.itheima;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {

    private Map<Class<?>, Object> map = new HashMap<>(); // singletonObjects

    public BeanFactoryImpl(Class<?> config) {
        if (config.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan cs = config.getAnnotation(ComponentScan.class);
            String packageName = cs.value();
            try {
                ComponentScanner.scan(this, packageName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void register(Class<?> clz) {
        try {
            Object obj = clz.getDeclaredConstructor().newInstance();
            map.put(clz, obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getBean(Class<T> clz) {
        Object obj = map.get(clz);
        return clz.cast(obj);
    }

    @Override
    public void autowired(Object a) {
        for (Field field : a.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> type = field.getType();
                Object b = map.get(type);
                try {
                    field.setAccessible(true);
                    field.set(a, b); // 依赖注入
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
