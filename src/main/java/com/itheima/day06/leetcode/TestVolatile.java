package com.itheima.day06.leetcode;

/**
 * JIT 是jvm的一个组件，负责优化java代码
 *    解释器 -> 解释执行class字节码 -> 变成机器码  效率低
 *    JIT 即时编译器 发现热点代码，把热点代码直接编译成机器码，并缓存之 效率高
 *    hotspot
 * 证明方式1: 禁用JIT  优化：-Xint
 * 证明方式2: 查看JIT优化后的代码：-XX:+PrintCompilation
 * 告诉JIT编辑器，当读写volatile修饰的变量时，不要优化，如果有优化也要废弃
 */
public class TestVolatile {
    /**
     *   线程使用的内存
     *       cpu  寄存器...  读写速度快  ns 10-9   ps 10-12
     *
     *   共享变量（static，堆中的java对象）
     *       使用物理内存...读写速度慢    几百纳秒
     */

    volatile static boolean stop = false; // 可见性问题，加了volatile可以保证共享变量的可见性和有序性

    private static void loop() {
        while (!stop) {
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            loop();
            while (!stop) {  // 优化成 while(true)
            }
            System.out.println(Thread.currentThread().getName() + "退出了循环");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop = true;
            System.out.println(Thread.currentThread().getName()+"修改stop为true");
        }, "t2");

        t1.start();
        t2.start();
    }
}
