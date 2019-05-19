package com.chengw.thread.challenge.visiablility;

import com.chengw.thread.tools.Tools;

/**
 * 线程启动与可见性
 * **/
public class ThreadStartVisibility {

    static  int data = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(){

            @Override
            public void run(){
                Tools.randomPause(50);

                System.out.println(data);
            }
        };

        data = 1;
        thread.start();
        Tools.randomPause(50);

        /**
         * 如果没有将该语句注释掉，
         * 父线程在子线程启动之后对共享变量的更新对子线程的可见性是没有保证的
         * */
        //data = 2;
    }

}
