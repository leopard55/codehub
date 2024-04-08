package com.itheima.day03.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 方法反射的性能
// --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/jdk.internal.reflect=ALL-UNNAMED
public class TestReflect {
    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        Method foo = TestReflect.class.getDeclaredMethod("foo", int.class);
        for (int i = 1; i <= 16; i++) {

            // 普通方法 new TestReflect().foo()  静态方法 TestReflect.foo();
            foo.invoke(null, i); // 静态方法反射调用，第一个（对象）参数传null

        }
        System.in.read(); // 让程序停在这里不要结束
    }

    static void foo(int i) {
        System.out.println("foo:" + i);
    }
}
