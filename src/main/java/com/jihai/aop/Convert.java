package com.jihai.aop;

public interface Convert<PARAM> {
    OperateLogDO convert(PARAM param);
}
