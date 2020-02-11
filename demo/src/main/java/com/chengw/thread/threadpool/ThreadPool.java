package com.chengw.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 手动创建线程池
 *
 * @author chengw
 */
public class ThreadPool {



    public static ThreadPoolExecutor getThreadPool() {

        /**
         * 线程池大小
         */
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        /**
         * 线程池最大大小
         */
        int maxPoolSize = 100;
        /**
         * 线程活动保持时间
         */
        long keepAliveTime = 1;

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                namedThreadFactory);
    }

}
