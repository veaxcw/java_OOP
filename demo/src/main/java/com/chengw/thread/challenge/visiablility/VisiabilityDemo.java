package com.chengw.thread.challenge.visiablility;

import com.chengw.thread.utils.Tools;


/**
 * 没有出现死循环的结果
 * 我也不知道为什么
 * **/
public class VisiabilityDemo {

    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(timeConsumingTask);
        thread.start();
        Thread.sleep(10000);
        timeConsumingTask.cancle();
    }


}

class TimeConsumingTask implements Runnable{

    private boolean toCancle = false;

    @Override
    public void run() {
        while (!toCancle){
            if(doExecute()){
                break;
            }
            if(toCancle){
                System.out.println("Task was canceled");
            }else {
                System.out.println("Task Done");
            }
        }
    }


    private boolean doExecute(){
        boolean isDone = false;
        System.out.println("executing......");

        Tools.randomPause(50);

        return isDone;

    }

    public void cancle(){
        toCancle = true;
        System.out.println(this + "canceled");
    }

}
