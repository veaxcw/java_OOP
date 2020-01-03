package com.chengw.thread.lifecycle;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengwei
 */
public class ThreadLifecycle {


    private static Executor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            100,
            1000L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("demo-thread-%d").build());

    private static File file = new File("/Users/chengwei/Downloads/Axure9003648.dmg");


    /**
     * 模拟阻塞线程操作
     * @param args
     */
    public static void main(String[] args) {

        int i = 0;
        while (i++ < 100) {
            executor.execute(ThreadLifecycle::lock);


        }
    }

    private static  synchronized void lock() {
        System.out.println(Thread.currentThread().getName() + " get the lock ");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " release the lock ");

    }

    // mac 没有文件读写权限导致FileNotFound
    private static void fileRead() {
        System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getState());
        try(InputStream ins = new FileInputStream(file)) {
            byte[] bytes = new byte[1024];

            while (ins.read(bytes) != -1) {
                System.out.println("继续读......");
                continue;
            }
            System.out.println("当前线程即将结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
