package com.chengw.thread.challenge.raceCondition;

import com.chengw.thread.tools.Tools;

/**
 * 竟态DEMO
 *
 *问题： 不同的业务线程拿到了相同的业务Id
 * */
public class RaceConditionDemo {

    public static void main(String[] args) {
        /*
        * 客户端线程数
        * **/
        int numberOfThreads = args.length > 0? Short.valueOf(args[0]):Runtime.getRuntime().availableProcessors();
        Thread[] workThreads = new Thread[numberOfThreads];
        for (int i =0;i < numberOfThreads;i++){
            /*
            * 每个线程有是个请求
            * **/
            workThreads[i] = new WorkThread(i,10);
        }
        for(Thread t:workThreads)
            t.start();
    }

    /*模拟工作线程**/
    static class WorkThread extends Thread{
         private final int requestCount;

        public WorkThread(int id,int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run(){
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGenerator = RequestIDGenerator.getInstance();
            while (i-- > 0){
                requestID = requestIDGenerator.nextID();
                processRequest(requestID);
            }
        }

        /**模拟请求处理*/
        private void processRequest(String requestId){
            Tools.randomPause(50);
            System.out.println(Thread.currentThread().getName() + "got requestId:" + requestId);
        }
    }
}
