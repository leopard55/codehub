package com.itheima.day06.leetcode;

public class LeetCode1115_2 {
    public static void main(String[] args) {
        FooBar bf = new FooBar(5);
        new Thread(() -> {
            bf.foo();
        }).start();

        new Thread(() -> {
            bf.bar();
        }).start();
    }

    static int flag = 1; // 1表示foo打印，2表示轮到bar打印
    static final Object lock = new Object(); // 加final能确保锁对象是同一个，只能赋值一次
    // 乐观锁-线程不停
    // 悲观锁-线程阻塞
    static class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo() {
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("foo ");
                    flag = 2;
                    lock.notify();
                }
            }
        }

        public void bar() {
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("bar ");
                    flag = 1;
                    lock.notify();
                }
            }
        }
    }
}
