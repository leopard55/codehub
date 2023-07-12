package com.itheima.aop;


public interface Advice {

    Object around(PJP pjp);

    // pjp.proceed()

}
