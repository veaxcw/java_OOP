package com.chengw.thread.cooperation.countDownLatch;

/**
 * @author chengw
 */
public interface Service {

    /**
     * 启动服务
     */
    void start();


    /**
     * 停止服务
     */
    void stop();

    /**
     * 服务是否启动
     * @return true:服务已经启动
     */
    boolean isStarted();

}
