package com.itheima.day06.leetcode;

import java.util.concurrent.Semaphore;

public class Leetcode1117_1 {

    static class H2O {
        Semaphore h = new Semaphore(2);
        Semaphore o = new Semaphore(0);

        public void h() {
            try {
                h.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("H");
            o.release();
        }

        public void o() {
            try {
                o.acquire(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("0 ");
            h.release(2);
        }
    }

    public static void main(String[] args) {
        H2O h2o = new H2O();
        new Thread(() -> {
            h2o.h();
        }).start();
        new Thread(() -> {
            h2o.h();
        }).start();
        new Thread(() -> {
            h2o.h();
        }).start();
        new Thread(() -> {
            h2o.h();
        }).start();
        new Thread(() -> {
            h2o.o();
        }).start();
        new Thread(() -> {
            h2o.o();
        }).start();
    }
}
