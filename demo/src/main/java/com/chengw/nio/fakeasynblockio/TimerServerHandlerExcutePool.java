package com.chengw.nio.fakeasynblockio;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * socket 服务端处理器线程池
 * @author chengw
 */
public class TimerServerHandlerExcutePool {

    private ExecutorService executorService;

    public TimerServerHandlerExcutePool(int maxPoolSice,int queueSize) {

        executorService = new  ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSice,
                120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<java.lang.Runnable>(queueSize));
    }

    public void execute(Runnable task){
        executorService.execute(task);
    }
}
