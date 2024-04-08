package com.itheima.test;

import com.itheima.aop.*;

import java.lang.reflect.Method;
import java.util.List;

public class Test3 {
    public static void main(String[] args) throws NoSuchMethodException {
        List<Advisor> advisorList = AspectProcessor.process(MyConfig.class);

        Method foo = Target.class.getDeclaredMethod("foo");
        PJP pjp = new PJP(advisorList, new Target(), foo, new Object[0]);
        pjp.proceed();
    }

    static class Target {
        public void foo() {
            System.out.println("Target.foo()");
        }
    }

    static class Advice1 implements Advice {

        @Override
        public Object around(PJP pjp) {
            System.out.println("Advice1.before()...");
            Object r = pjp.proceed();
            System.out.println("Advice1.after()...");
            return r;
        }
    }

    static class Advice2 implements Advice {

        @Override
        public Object around(PJP pjp) {
            System.out.println("Advice2.before()...");
            Object r = pjp.proceed();
            System.out.println("Advice2.after()...");
            return r;
        }
    }
}
