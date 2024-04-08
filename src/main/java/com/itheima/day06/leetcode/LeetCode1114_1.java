package com.itheima.day06.leetcode;

public class LeetCode1114_1 {
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

    static boolean t1End = false; //表示t1没运行完
    static boolean t2End = false; //表示t2没运行完

    static Object lock = new Object(); // 锁

    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                foo.first();
                t1End = true;
                lock.notifyAll(); // 把等待的线程t2,t3全部唤醒
            }
        }, "t1");
        // 条件不满足，暂停执行
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                while (!t1End) {
                    try {
                        lock.wait(); // 当前线程进入等待状态，并且会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                foo.second();
                t2End = true;
                lock.notifyAll();
            }
        }, "t2");
        // 条件不满足，暂停执行
        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                while (!t2End) { // 我虽然被叫醒了，但是发现叫醒的不是我
                    try {
                        lock.wait(); // 当前线程进入等待状态，并且会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                foo.third();
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
