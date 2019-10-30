package com.chengw.designPattern.observerPattern.inner.display.impl;

import com.chengw.designPattern.observerPattern.inner.display.DisplayElement;
import com.chengw.designPattern.observerPattern.inner.subject.ConcreteSubject;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author chengwei
 */
public class GovObserverImpl implements Observer, DisplayElement {

    private Observable observable;

    private float temperature;

    private String warningLevel;


    public GovObserverImpl(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ConcreteSubject) {
            ConcreteSubject concreteSubject = (ConcreteSubject) o;
            this.temperature = concreteSubject.getTemperature();
            this.warningLevel = concreteSubject.getWarningLevel();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("gov get temperature: " + this.temperature);
        System.out.println("gov warnLevel: " + this.warningLevel);
    }
}
