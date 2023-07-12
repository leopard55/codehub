package com.itheima.aop;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public interface Advisor {

    Advice advice();

    AspectJExpressionPointcut pointcut();

}
