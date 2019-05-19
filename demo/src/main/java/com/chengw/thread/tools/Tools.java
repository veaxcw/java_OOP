package com.chengw.thread.tools;

import java.util.Date;
import java.util.Random;

public class Tools {

    /**
     * 让线程随机暂停一段时间
     * **/
    public static void randomPause(int time){
        Random r = new Random();
        r.setSeed(new Date().getTime());

        try {
            Thread.sleep(r.nextInt(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
