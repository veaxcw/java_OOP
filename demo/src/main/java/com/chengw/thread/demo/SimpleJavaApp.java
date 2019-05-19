package com.chengw.thread.demo;

import java.util.Date;

public class SimpleJavaApp {

    public static void main(String[] args) throws InterruptedException {
        while (true){
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }

}
