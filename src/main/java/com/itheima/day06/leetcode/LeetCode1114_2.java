package com.itheima.day06.leetcode;

import java.util.concurrent.CountDownLatch;

public class LeetCode1114_2 {
    static class Foo {
        public void first() {
            System.out.println("1");
        }

        public void second() {
            System.out.println("2");
        }

        public void third() {
            System.out.println("3");
        }
    }

    static CountDownLatch latch2 = new CountDownLatch(1);
    static CountDownLatch latch3 = new CountDownLatch(1);

    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> {
            foo.first();
            latch2.countDown();
        }, "t1");
        // 条件不满足，暂停执行
        Thread t2 = new Thread(() -> {
            try {
                latch2.await(); // 等待latch2减为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.second();
            latch3.countDown();
        }, "t2");
        // 条件不满足，暂停执行
        Thread t3 = new Thread(() -> {
            try {
                latch3.await(); // 等待latch3减为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.third();
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
