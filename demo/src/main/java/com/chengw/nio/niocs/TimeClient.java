package com.chengw.nio.niocs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengw
 */
public class TimeClient {

    private static final int  port = 9999;

    private static final String HOST = "localhost";

    private static final Executor EXECUTOR =  new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),100,
                120L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100),new ThreadFactoryBuilder().setNameFormat("client-%d").build());



    public static void main(String[] args) {

        TimerClientHandle timerClientHandle = new TimerClientHandle(HOST,port);

        for(int i = 0;i < 1;i++){
            EXECUTOR.execute(timerClientHandle);
        }

    }

}
