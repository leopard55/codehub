package com.itheima.day06.leetcode;

import java.util.concurrent.Semaphore;

public class Leetcode1116_2 {
    static class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        Semaphore s0 = new Semaphore(1); // zero
        Semaphore s1 = new Semaphore(0); // odd
        Semaphore s2 = new Semaphore(0); // even

        // 010203040506
        public void zero() {
            for (int i = 0; i < n; i++) {
                try {
                    s0.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(0);
                if (i % 2 == 0) { // 轮到odd
                    s1.release(); // 可以在0把锁的基础上加一把
                } else { // 轮到even打印
                    s2.release();
                }
            }
        }

        public void odd() {
            for (int i = 1; i <= n; i += 2) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                s0.release();
            }
        }

        public void even() {
            for (int i = 2; i <= n ; i+=2) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                s0.release();
            }
        }

        public static void main(String[] args) {
            ZeroEvenOdd zeo = new ZeroEvenOdd(6);

            new Thread(() -> {
                zeo.zero();
            }, "A").start();

            new Thread(() -> {
                zeo.odd();
            }, "B").start();

            new Thread(() -> {
                zeo.even();
            }, "C").start();
        }

    }
}
