package com.itheima.test;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

public class Test2 {
    public static void main(String[] args) throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

        Method foo = A.class.getDeclaredMethod("bar");

        System.out.println(pointcut.matches(foo, A.class));
    }
}
