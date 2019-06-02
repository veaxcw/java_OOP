package com.chengw.thread.challenge.visiablility;

import com.chengw.thread.utils.Tools;

/**
 * 线程终止之后该线程对共享变量的更改对于调用该线程的join方法的线程而言 是可见的
 * **/
public class ThreadJoinVisibility {

    static  int data = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(){

            @Override
            public void run(){
                Tools.randomPause(50);

                data = 1;
            }
        };

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tools.randomPause(50);

        System.out.println(data);
    }


}
