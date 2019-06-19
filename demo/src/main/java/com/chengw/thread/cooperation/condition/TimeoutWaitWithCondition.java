package com.chengw.thread.cooperation.condition;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengw
 */
public class TimeoutWaitWithCondition {

    private static final Lock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    private static  boolean ready = false;

    private static Random r = new Random(System.currentTimeMillis());

    public static void waiter(final long timeOut) throws InterruptedException {
        if(timeOut < 0){
            throw new IllegalArgumentException();
        }
        lock.lock();
        try {
            final Date deadline = new Date(System.currentTimeMillis() + timeOut);
            boolean continueToWait = true;

            while(!ready){
                Debug.info("still not ready,continue to wait:"+ timeOut);
                if(!continueToWait){
                    Debug.error("等待超时");
                    return;
                }
                /*
                * 等待超时则返回false
                * 否则返回true
                * */
                continueToWait = condition.awaitUntil(deadline);
                Debug.info("执行目标程序");
            }
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
           for(;;) {
               lock.lock();
               try {
                   ready = r.nextInt(100) < 5?true:false;
                   if(ready){
                       condition.signal();
                   }
               } finally {
                lock.unlock();
               }

               Tools.randomPause(500);
           }
        });
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

}
