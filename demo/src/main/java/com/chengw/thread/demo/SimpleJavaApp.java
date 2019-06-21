package com.chengw.thread.demo;

import java.util.Date;

/**
 * @author chengw
 */
public class SimpleJavaApp {

    public static void main(String[] args) throws InterruptedException {
        synchronized (SimpleJavaApp.class){

        }
        method();
    }

    private static void method() throws InterruptedException {
        while (true){
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }

}
