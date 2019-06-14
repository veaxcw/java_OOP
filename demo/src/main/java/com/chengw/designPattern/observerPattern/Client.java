package com.chengw.designPattern.observerPattern;

import java.util.Random;

/**
 * @author chengw
 */
public class Client {

    public static void main(String[] args) {
        ISubject subject = new Subject();
        subject.add(new PersonOberver());
        subject.add(new GovObserver());
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for(int i = 0;i < 100;i++){
            subject.setTemperature(r.nextInt(50));
        }
    }

}
