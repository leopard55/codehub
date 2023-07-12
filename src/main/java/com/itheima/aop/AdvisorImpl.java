package com.itheima.aop;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class AdvisorImpl implements Advisor {

    private Advice advice;
    private AspectJExpressionPointcut pointcut;

    public AdvisorImpl(Advice advice, AspectJExpressionPointcut pointcut) {
        this.advice = advice;
        this.pointcut = pointcut;
    }

    @Override
    public Advice advice() {
        return advice;
    }

    @Override
    public AspectJExpressionPointcut pointcut() {
        return pointcut;
    }
}
