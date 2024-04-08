package com.itheima.day03.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// JDK动态代理内部就是这么实现的
public class TestProxy {

    public static void main(String[] args) {
        ClassLoader cl = TestProxy.class.getClassLoader();
        Proxy.newProxyInstance(cl, new Class[]{MyService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        Proxy$1 proxy$1 = new Proxy$1(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 1.写一些增强逻辑
                System.out.println("before...");

                // MyService接口中的save()
                // public abstract void com.itheima.day03.proxy.TestProxy$MyService.save()
                System.out.println(method);
                // 方法反射调用。  代用目标的save()
                return method.invoke(new Target(), args);
            }
        });
        proxy$1.save();

    }

    interface MyService {
        void save();
    }

    static class Target implements MyService {

        @Override
        public void save() {
            System.out.println("target save");
        }

    }

    /**
     * Proxy$1 -> InvocationHandler -> Target
     * Proxy$1在运行期间由java代码动态生成的，没有java源代码，直接生成*.class字节码（代理类生成的字节码）。
     * 用ClassLoader加载。不用java源代码到.class编译
     */
    static class Proxy$1 implements MyService {

        // 让代理类和目标不要紧密的结合在一起,代理类不直接跟目标打交道
        //private Target target = new Target();

        InvocationHandler h;

        public Proxy$1(InvocationHandler h) {
            this.h = h;
        }

        @Override
        public void save() {
            // 1.写一些增强逻辑, 写在invoke里更好一些。增强逻辑，怎么调目标，是InvocationHandler需要干的活
            System.out.println("before...");
            // 2.调用目标的save方法。   不能直接调目标，调用InvocationHandler
            //target.save();

            try {
                h.invoke(this, MyService.class.getDeclaredMethod("save"), new Object[0]);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
