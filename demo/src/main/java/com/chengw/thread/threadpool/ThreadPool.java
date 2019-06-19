package com.chengw.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建线程池
 * @author chengw
 */
public class ThreadPool {


    /**
     * 线程池大小
     */
    private static int corePoolSize = 10;

    /**
     * 线程池最大大小
     */
    private static int maxnumPoolSize = 100;

    /**
     * 线程活动保持时间
     */
    private static long keepAliveTime = 1;

    /**
     * 任务队列
     */
    private static BlockingQueue workQuene = new ArrayBlockingQueue(10);

    public static ThreadPoolExecutor getThreadPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxnumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQuene);
        return executor;
    }

}
