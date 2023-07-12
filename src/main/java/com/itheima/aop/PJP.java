package com.itheima.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PJP {

    private List<Advisor> advisorList; // 切面集合(切面1，切面2)
    private Object target; // 目标对象
    private Method method; // 方法对象
    private Object[] args; // 方法参数

    public PJP(List<Advisor> advisorList, Object target, Method method, Object[] args) {
        this.advisorList = advisorList;
        this.target = target;
        this.method = method;
        this.args = args;
    }

    private int index = 0;

    // 一次处理一个切面，如果没有更多切面，调用目标方法
    public Object proceed() {
        // 还有没处理完的切面
        while (index < advisorList.size()) {
            Advisor advisor = advisorList.get(index++); // 切面2 index=2
            if (!advisor.pointcut().matches(method, target.getClass())) {
                continue;
            }
            return advisor.advice().around(this);
        }
        // 调用目标方法
        try {
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
