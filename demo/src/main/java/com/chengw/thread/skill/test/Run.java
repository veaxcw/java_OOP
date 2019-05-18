package com.chengw.thread.skill.test;

import com.chengw.thread.skill.extthread.MyThread;

/**
 *  * println 中的线程安全问题
 *  并没有出现书中描述的问题
 * **/
public class Run {

    public static void main(String[] args) {
        MyThread run = new MyThread();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        Thread t3 = new Thread(run);
        Thread t4 = new Thread(run);
        Thread t5 = new Thread(run);

        t1.run();
        t2.run();
        t3.run();
        t4.run();
        t5.run();
    }
}
