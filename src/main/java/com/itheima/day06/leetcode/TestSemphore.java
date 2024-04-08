package com.itheima.day06.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

// 信号量
public class TestSemphore {
    // synchronized Lock 独占锁，同一时刻只能有一个线程获得锁
    // Semaphore 共享锁 同一时刻可以有多个线程同时获得锁，可以设置线程的上限

    public static void main(String[] args) {
        Semaphore lock = new Semaphore(3);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int k = i; // 如果能保证k的值不会发生改变，那么fianl可以省略
            Thread t = new Thread(() -> { // lambda 表达式如果使用外界局部变量，此变量必须是常量
                try {
                    lock.acquire(); // 获取Semaphore锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // System.out.println(i);
                System.out.println(k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.release(); // 释放Semaphore锁
            });
            threads.add(t);
        }
        for (Thread thread : threads) {
            thread.start();
        }

    }
}
