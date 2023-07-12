package com.itheima;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {

    private Map<Class<?>, Object> map = new HashMap<>(); // singletonObjects

    private List<BeanPostProcessor> list = new ArrayList<>();

    public BeanFactoryImpl(Class<?> config, BeanPostProcessor... processors) {
        list.addAll(List.of(processors));
        if (config.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan cs = config.getAnnotation(ComponentScan.class);
            String packageName = cs.value();
            try {
                ComponentScanner.scan(this, packageName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // 原始对象 a， b
            /*map.forEach((clz,bean)->{
                // a -> a 代理 -> a @Autowired -> a @Resource
                for (BeanPostProcessor beanPostProcessor : list) {
                    beanPostProcessor.enhance(this, bean);
                }
            });*/

            for (BeanPostProcessor beanPostProcessor : list) {
                for (Object bean : map.values()) {
                    beanPostProcessor.enhance(this, bean);
                }
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
    public void replace(Class<?> clz, Object proxy) {
        map.put(clz, proxy);
    }
}
