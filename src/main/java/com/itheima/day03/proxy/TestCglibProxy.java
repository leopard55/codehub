package com.itheima.day03.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCglibProxy {
    public static void main(String[] args) {

        MyService service = new MyService(); // 目标类

        //增强器
        Enhancer enhancer = new Enhancer();
        // 1.设置父类（父类不能加final，因为要生成子类）
        enhancer.setSuperclass(MyService.class);
        // 2.设置方法拦截器  增强功能
        enhancer.setCallback(new MethodInterceptor() {
            // 参数1 = 代理对象
            // 参数2 = 方法对象
            // 参数3 = 方法参数数组
            // 参数4 = 方法代理
            @Override
            public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                // 1) method来反射调用了目标方法 （第17次时，替换了内部实现，反射变正常了）
                //return method.invoke(service, objects);

                // 2) 性能高（一上来，就使用正常方式调用目标对象方法），代价生成更多的字节码文件，2个类。配合目标用。 Spring使用该方法
                //return methodProxy.invoke(service, objects);

                // 3) 性能高（一上来，就使用正常方式调用父类对象方法）。配合代理用。可以不依赖于目标也能正常工作
                return methodProxy.invokeSuper(proxy, objects);
            }
        }); // 设置增强功能

        // 3.创建代理对象，是MyService的子类对象
        MyService proxy = (MyService) enhancer.create();
        proxy.foo();
    }

    static class MyService {
        public void foo() {
            System.out.println("MyService.foo()");
        }
    }
}
