package com.chengw.designpattern.observer.diy;

import com.chengw.designpattern.observer.diy.observer.impl.GovObserver;
import com.chengw.designpattern.observer.diy.observer.impl.PersonOberver;
import com.chengw.designpattern.observer.diy.subject.Subject;
import com.chengw.designpattern.observer.diy.subject.impl.ConcreteSubject;

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
