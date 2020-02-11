package com.chengw.thread.challenge.deadLock;

import java.util.concurrent.locks.Lock;

public class DeadLock {

    public static String str1 = "str1";
    public static String str2 = "str2";


    public static void main(String[] args) {
        Thread a = new Thread(()->{
            try {
                while (true){
                    synchronized(DeadLock.str1){
                        System.out.println(Thread.currentThread().getName() + "锁住str1");
                        Thread.sleep(1000);
                        synchronized (DeadLock.str2){
                            System.out.println(Thread.currentThread().getName() + "锁住str2");
                            Thread.sleep(1000);
                        }
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread b = new Thread(()->{
            try {
                while (true){
                    synchronized (DeadLock.str2){
                        System.out.println(Thread.currentThread().getName() + "锁住str2");
                        Thread.sleep(1000);
                        synchronized(DeadLock.str1){
                            System.out.println(Thread.currentThread().getName() + "锁住str1");
                            Thread.sleep(1000);
                        }
                    }


                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.start();
        b.start();
    }

}
