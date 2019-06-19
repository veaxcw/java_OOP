package com.chengw.thread.lock.fairLock;

import com.chengw.thread.threadpool.ThreadPool;
import com.chengw.thread.utils.Debug;
import org.springframework.core.task.support.ExecutorServiceAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengw
 */
public class FairLock {

    private ReentrantLock lock;


    public FairLock() {
        this(false);
    }

    public FairLock(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void service(){
        try {
            lock.lock();
            Debug.info("获得锁定");
            Debug.info("执行业务代码");
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            Debug.info("释放锁");
        }
    }

    public static void main(String[] args) {
        final FairLock fairLock = new FairLock(true);

        Thread t = new Thread(()->{
            fairLock.service();
        });

        ExecutorService exec = ThreadPool.getThreadPool();


        for(int i = 0;i < 5;i++){
            exec.execute(t);
        }
        exec.shutdown();

    }

}
