package com.chengw.thread.lock.volatiledemo;

/**
 * 读写锁：
 * 允许多个线程读共享变量，但是却同时只允许一个线程写
 *
 * @author chengw**/
public class Counter {

    private volatile  long count;

    public long value(){
        return count;
    }

    public void increment(){
        synchronized (this){
            count++;
        }
    }

}
