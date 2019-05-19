package com.chengw.thread.lock.explicitLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程转储 显式锁信息示例程序
 * **/
public class ExplicitLockInfo {

    private static final Lock  lock = new ReentrantLock();
    private static int sharedData = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            lock.lock();
            try {
                try {
                    Thread.sleep(22000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedData = 1;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t.start();
        Thread.sleep(100);
        lock.lock();
        try {
            System.out.println("shareData:" + sharedData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
