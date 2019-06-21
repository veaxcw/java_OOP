package com.chengw.thread.lock.cas;

import com.chengw.thread.utils.Tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * @author chengw
 */
public class CasBasedCounter {

    private volatile long count;
    private AtomicLongFieldUpdater<CasBasedCounter> fieldUpdater;

    public CasBasedCounter(){
        fieldUpdater = AtomicLongFieldUpdater.newUpdater(CasBasedCounter.class,"count");
    }

    public long value(){

        return count;
    }


    public void increment(){

        long oldValue;
        long newValue;

        do{
            oldValue = count;
            newValue = oldValue + 1;
        }while ( !compareAndSwap(oldValue,newValue));

    }


    private boolean compareAndSwap(long oldValue, long newValue){

        return fieldUpdater.compareAndSet(this,oldValue,newValue);

    }

    public static void main(String[] args) throws InterruptedException {
        final CasBasedCounter counter  = new CasBasedCounter();
        Thread t;
        Set<Thread> threads = new HashSet<>();
        /**新建20个写 线程**/
        for(int i =0;i < 20;i++){
            t = new Thread(() -> {
                Tools.randomPause(50);
                counter.increment();
            });
            threads.add(t);
        }

        for(int i =0;i < 8;i++){
            t = new Thread(() -> {
                Tools.randomPause(50);
                System.out.println("当前值：" + counter.value());
            });
            threads.add(t);
        }

        Tools.startAndWaitTerminated(threads);
        System.out.println("final count:" + counter.value());



    }


}
