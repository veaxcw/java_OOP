package com.chengw.designPattern.observerPattern.inner;

import com.chengw.designPattern.observerPattern.inner.display.DisplayElement;
import com.chengw.designPattern.observerPattern.inner.display.impl.GovObserverImpl;
import com.chengw.designPattern.observerPattern.inner.observer.GovObserver;
import com.chengw.designPattern.observerPattern.inner.observer.PersonObserver;
import com.chengw.designPattern.observerPattern.inner.subject.ConcreteSubject;

import java.util.Random;

/**
 * @author chengw
 */
public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        DisplayElement govObserver = new GovObserverImpl(subject);
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for(int i = 0;i < 100;i++){
            subject.setTemperature(r.nextInt(50));
        }
    }

}
