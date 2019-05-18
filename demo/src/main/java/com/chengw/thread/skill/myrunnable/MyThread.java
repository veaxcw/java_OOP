package com.chengw.thread.skill.myrunnable;

public class MyThread extends Thread {

    private int count = 5;

    private String name;


    public MyThread(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        while(count > 0){
            count--;
            System.out.println(this.currentThread().getName() + ":" + count);
        }
    }


}
