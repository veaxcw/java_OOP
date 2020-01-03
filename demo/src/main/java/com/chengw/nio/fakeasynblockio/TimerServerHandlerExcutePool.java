package com.chengw.nio.fakeasynblockio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * socket 服务端处理器线程池
 * @author chengw
 */
public class TimerServerHandlerExcutePool {

    private ExecutorService executorService;

    public TimerServerHandlerExcutePool(int maxPoolSice,int queueSize) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        executorService = new  ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSice,
                120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<java.lang.Runnable>(queueSize),namedThreadFactory);
    }

    public void execute(Runnable task){
        executorService.execute(task);
    }
}
