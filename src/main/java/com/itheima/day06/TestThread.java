package com.itheima.day06;

public class TestThread {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
    }

}
