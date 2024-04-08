package com.itheima.day06.leetcode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

public class LeetCode1114_3 {
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

    static SynchronousQueue<Integer> queue2 = new SynchronousQueue<>();
    static SynchronousQueue<Integer> queue3 = new SynchronousQueue<>();

    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> {
            foo.first();
            try {
                queue2.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        // 条件不满足，暂停执行
        Thread t2 = new Thread(() -> {
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.second();
            try {
                queue3.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        // 条件不满足，暂停执行
        Thread t3 = new Thread(() -> {

        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
