package com.itheima.day06.leetcode;

public class Leetcode1226_1 {

    static class Lock {
        int i;

        public Lock(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "筷子" + i;
        }
    }

    static class DiningPhilosophres {

        Object[] locks = {
                new Lock(0), //筷子0
                new Lock(1), //筷子1
                new Lock(2), //筷子2
                new Lock(3), //筷子3
                new Lock(4)  //筷子4
        };

        public void wantsToEat(int i) {
            Object left = left(i);
            Object right = right(i);
            synchronized (left) {
                synchronized (right) {
                    System.out.println(Thread.currentThread().getName() + " 吃饭!");
                }
            }
        }

        public Object left(int i) {
            if (i == 4) {
                return locks[0];
            }
            return locks[i];
        }

        public Object right(int i) {
            if (i == 4) {
                return locks[4];
            }
            return locks[i + 1];
        }
    }

    public static void main(String[] args) {
        DiningPhilosophres d = new DiningPhilosophres();
        new Thread(() -> {d.wantsToEat(0);},"哲学家0").start(); // 0 1 这两把锁
        new Thread(() -> {d.wantsToEat(1);},"哲学家1").start(); // 1 2 这两把锁
        new Thread(() -> {d.wantsToEat(2);},"哲学家2").start(); // 2 3 这两把锁
        new Thread(() -> {d.wantsToEat(3);},"哲学家3").start(); // 3 4 这两把锁
        new Thread(() -> {d.wantsToEat(4);},"哲学家4").start(); // 4 0 这两把锁
    }
}
