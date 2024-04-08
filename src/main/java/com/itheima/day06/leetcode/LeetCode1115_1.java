package com.itheima.day06.leetcode;

public class LeetCode1115_1 {
    public static void main(String[] args) {
        FooBar bf = new FooBar(5);
        new Thread(() -> {
            bf.foo();
        }).start();

        new Thread(() -> {
            bf.bar();
        }).start();
    }

    volatile static int flag = 1; // 1表示foo打印，2表示轮到bar打印

    // 乐观锁-线程不停
    // 悲观锁-线程阻塞
    static class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo() {
            for (int i = 0; i < n; i++) {
                // 等待
                while (flag != 1) {}
                System.out.println("foo");
                flag = 2;
            }
        }

        public void bar() {
            for (int i = 0; i < n; i++) {
                // 等待
                while (flag != 2) {}
                System.out.println("bar");
                flag = 1;
            }
        }
    }
}
