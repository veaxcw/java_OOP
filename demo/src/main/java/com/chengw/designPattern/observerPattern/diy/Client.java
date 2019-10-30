package com.chengw.designPattern.observerPattern.diy;

import com.chengw.designPattern.observerPattern.diy.observer.impl.GovObserver;
import com.chengw.designPattern.observerPattern.diy.observer.impl.PersonOberver;
import com.chengw.designPattern.observerPattern.diy.subject.Subject;
import com.chengw.designPattern.observerPattern.diy.subject.impl.ConcreteSubject;

import java.util.Random;

/**
 * @author chengw
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.add(new PersonOberver());
        subject.add(new GovObserver());
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for(int i = 0;i < 100;i++){
            subject.setTemperature(r.nextInt(50));
        }
    }

}
