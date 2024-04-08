package com.itheima.day06.leetcode;

import java.util.concurrent.CountDownLatch;

// 倒计时锁
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2); //latch.countDown() 让倒计时减1

        new Thread(() -> {
            System.out.println("倒计时开始");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("倒计时已经结束");
        });

        System.out.println();
    }
}
