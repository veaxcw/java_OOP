package com.chengw.thread.lock.volatiledemo;

/**
 * volatile 可见性保证
 * @author chengw
 */
public class VolatileViableDemo {
    /**
     * volatile 保证了 其他线程对变量的可见性
     * 也就是 当前线程在操作结束后必须将变量的值存入主内存后,
     * 其他线程再操作之前必须从主内存中读取变量的值
     * **/

    private static volatile int finished = 0;

    //private static int finished = 0;

    private static void check(){
        while (finished == 0){
            System.out.println("不可见吧 哈哈哈哈");
        }
        System.out.println("我变成一了 哈哈哈哈");
    }

    private static void finish(){
        finished = 1;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->check()).start();
        Thread.sleep(100);
        finish();
    }

}
