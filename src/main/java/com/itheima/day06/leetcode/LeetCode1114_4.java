package com.itheima.day06.leetcode;

import java.util.concurrent.SynchronousQueue;

public class LeetCode1114_4 {
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

    volatile static boolean t1End = false;
    volatile static boolean t2End = false;

    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> {
            foo.first();
            t1End = true;
        }, "t1");
        // 条件不满足，暂停执行
        Thread t2 = new Thread(() -> {
            while (!t1End) {
                Thread.yield();
            }
            foo.second();
            t2End = true;

        }, "t2");
        // 条件不满足，暂停执行
        Thread t3 = new Thread(() -> {
            while (!t2End) {
                Thread.yield();
            }
            foo.third();
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
