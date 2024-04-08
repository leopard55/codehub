package com.itheima.test;

import com.itheima.ioc.ComponentScan;
import com.itheima.aop.Advice;
import com.itheima.aop.Aspect;

@ComponentScan("com.test")
public class MyConfig {

    @Aspect("execution(* foo())")
    public static Advice advice1() {
        return pjp -> {
            System.out.println("Advice1.before()...");
            Object r = pjp.proceed();
            System.out.println("Advice1.after()...");
            return r;
        };
    }

    @Aspect("execution(* foo())")
    public static Advice advice2() {
        return pjp -> {
            System.out.println("Advice2.before()...");
            Object r = pjp.proceed();
            System.out.println("Advice2.after()...");
            return r;
        };
    }

    @Aspect("execution(* bar())")
    public static Advice advice3() {
        return pjp -> {
            System.out.println("Advice3.before()...");
            Object r = pjp.proceed();
            System.out.println("Advice3.after()...");
            return r;
        };
    }
}