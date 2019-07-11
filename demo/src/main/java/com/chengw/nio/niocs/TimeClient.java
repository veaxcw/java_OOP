package com.chengw.nio.niocs;

/**
 * @author chengw
 */
public class TimeClient {

    private static final int  port = 9999;

    private static final String HOST = "localhost";



    public static void main(String[] args) {

        TimerClientHandle timerClientHandle = new TimerClientHandle(HOST,port);

        for(int i = 0;i < 1;i++){
            new Thread(timerClientHandle,"客户端"+i).start();
        }

    }

}
