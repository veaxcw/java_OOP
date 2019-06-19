package com.chengw.thread.cooperation.countDownLatch;

import com.chengw.thread.utils.Debug;
import lombok.Data;

import java.util.concurrent.CountDownLatch;

/**
 * @author veax
 */
@Data
public abstract class AbstractService implements Service {

    private boolean isStarted = false;

    protected final CountDownLatch latch;

    /**
     * 留给子类实现的抽象方法，用于实现服务器的启动逻辑
     * @throws Exception
     */
    protected abstract void doStart() throws Exception;

    public AbstractService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void start() {
         new ServiceStarter().start();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }



    class ServiceStarter extends Thread {

        @Override
        public void run() {
            final String serviceName = AbstractService.this.getClass().getSimpleName();
            Debug.info("Starting :" + serviceName);
            try {
                doStart();
                isStarted = true;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
                Debug.info(serviceName + "服务启动");
            }

        }
    }
}
