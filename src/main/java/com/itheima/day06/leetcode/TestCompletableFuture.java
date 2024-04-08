package com.itheima.day06.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Slf4j
public class TestCompletableFuture {
    public static void main(String[] args) throws IOException {

        // ForkAndJoin 线程都是守护线程，没有main线程运行时，它们就会结束
        CompletableFuture.supplyAsync((Supplier<Object>) () -> {
            log.debug("任务1");
            return "task1";
        }).thenAccept((r -> {
            log.debug("{}", r);
        }));

        CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> "结果1");
        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> "结果2");

        CompletableFuture.allOf(c1, c2).thenRun(() -> {
            log.debug("{}", c1.join());
            log.debug("{}", c2.join());
        });

        System.in.read(); //让主线程不要立刻结束
    }
}
