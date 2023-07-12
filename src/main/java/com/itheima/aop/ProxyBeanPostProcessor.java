package com.itheima.aop;

import com.itheima.BeanFactory;
import com.itheima.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class ProxyBeanPostProcessor implements BeanPostProcessor {

    private final List<Advisor> advisorList;

    public ProxyBeanPostProcessor(Class<?> clz) {
        advisorList = AspectProcessor.process(clz);
    }

    @Override
    public void enhance(BeanFactory beanFactory, Object bean) {
        // bean 是原始对象(目标)
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{HeimaProxy.class});
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("target") && method.getReturnType().equals(Object.class) && method.getParameterCount() == 0) {
                    return bean; // 返回原始目标
                }
                PJP pjp = new PJP(advisorList, bean, method, args);
                return pjp.proceed();
            }
        });
        // 代理对象
        Object proxy = enhancer.create();
        beanFactory.replace(bean.getClass(), proxy);
    }
}
