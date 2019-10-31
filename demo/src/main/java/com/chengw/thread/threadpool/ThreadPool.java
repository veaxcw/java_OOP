package com.chengw.thread.threadpool;

import java.util.concurrent.*;

/**
 * 手动创建线程池
 * @author chengw
 */
public class ThreadPool {


    /**
     * 任务队列
     */
    private static BlockingQueue workQueue = new ArrayBlockingQueue(10);

    public static ThreadPoolExecutor getThreadPool(){
        /**
         * 线程池大小
         */
        int corePoolSize = 10;
        /**
         * 线程池最大大小
         */
        int maxPoolSize = 100;
        /**
         * 线程活动保持时间
         */
        long keepAliveTime = 1;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue);
        return executor;
    }

}
