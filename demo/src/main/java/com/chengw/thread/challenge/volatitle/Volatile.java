package com.chengw.thread.challenge.volatitle;

import com.chengw.thread.threadpool.ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengwei
 */
public class Volatile {

    private  volatile long cnt = 0;


    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor pool = ThreadPool.getThreadPool();

        Volatile demo = new Volatile();

        Thread thread1 = new Thread(() -> demo.update(20000));

        Thread thread2 = new Thread(() -> demo.update(20000));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        //pool.execute(() -> demo.update(20000));
        //pool.execute(() -> demo.update(20000));

        System.out.println(demo.cnt);


    }

     void  update(int limit) {

        for (int i = 0; i < limit; i++) {
            this.cnt++;
        }
    }


}
