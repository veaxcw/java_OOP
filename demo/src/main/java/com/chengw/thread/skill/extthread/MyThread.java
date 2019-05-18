package com.chengw.thread.skill.extthread;

/**
 * println 中的线程安全问题
 * **/
public class MyThread extends Thread {

    private int i = 5;

    @Override
    public void run() {
        System.out.println("i:" + i-- + "threadName:" + Thread.currentThread().getName());

    }


}

