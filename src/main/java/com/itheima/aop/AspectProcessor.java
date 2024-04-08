package com.itheima.aop;

import com.itheima.test.MyConfig;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AspectProcessor {
    public static List<Advisor> process(Class<?> clz) {
        List<Advisor> list = new ArrayList<>();
        Method[] declaredMethods = clz.getDeclaredMethods();
        Arrays.sort(declaredMethods, Comparator.comparing(Method::getName));
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Aspect.class)) {
                // 准备切点对象
                Aspect aspect = method.getAnnotation(Aspect.class);
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(aspect.value());
                // 获取通知对象
                method.setAccessible(true);
                try {
                    Advice advice = (Advice) method.invoke(null);
                    // 组合切面对象
                    list.add(new AdvisorImpl(advice, pointcut));                    ;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Advisor> advisorList = process(MyConfig.class);
        for (Advisor advisor : advisorList) {
            System.out.println(advisor.pointcut() + " " + advisor.advice());
        }
    }
}
