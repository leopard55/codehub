package com.itheima.day06.leetcode;

import java.util.concurrent.SynchronousQueue;

// 同步队列
public class TestSynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        // 最多放一个元素
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        queue.put(1); // 放元素，没有取就会阻塞
        queue.take();   // 获取元素，如果没有元素就阻塞

        new Thread(() -> {
            Integer take = null;
            try {
                queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }).start();

        System.out.println();
    }
}
