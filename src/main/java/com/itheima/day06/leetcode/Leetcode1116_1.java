package com.itheima.day06.leetcode;

public class Leetcode1116_1 {
    static class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        private int flag; // 0 zero, 1 odd, 2 even
        private final Object lock = new Object();
        // 010203040506
        public void zero() {
            // i=0   i=1
            // 0     0
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (flag != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("0");
                    if (i % 2 == 1) { // i 是奇数，轮到even来执行
                        flag  = 2;
                    } else {  // i 是偶数，轮到odd来执行
                        flag = 1;
                    }
                    lock.notifyAll();
                }
            }
        }

        public void odd() {
            for (int i = 1; i <= n; i += 2) {
                synchronized (lock) {
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(i);
                    flag = 0;
                    lock.notifyAll();
                }
            }
        }

        public void even() {
            for (int i = 2; i <= n ; i+=2) {
                synchronized (lock) {
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(i);
                    flag = 0;
                    lock.notifyAll();
                }
            }
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