package org.leopard.task;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class Task {

    @Async
    public void sayHello(String name) {
        LoggerFactory.getLogger(Task.class).info(name + ":Hello World!");
    }


    @Async
    public Future<String> taskOne() throws Exception {
        System.out.println("任务一" + Thread.currentThread().getName());
        return new AsyncResult<>("任务一执行完成");

    }

    @Async
    public Future<String> taskTwo() throws Exception {
        System.out.println("任务二" + Thread.currentThread().getName());
        return new AsyncResult<>("任务二执行完成");
    }

    @Async
    public void task1() {
        LoggerFactory.getLogger(Task.class).info("任务1" + ":Hello World!");
        System.out.println("任务1" + Thread.currentThread().getName());
    }

    @Async
    public void task2() {
        LoggerFactory.getLogger(Task.class).info("任务2" + ":Hello World!");
        System.out.println("任务2" + Thread.currentThread().getName());
    }
}
