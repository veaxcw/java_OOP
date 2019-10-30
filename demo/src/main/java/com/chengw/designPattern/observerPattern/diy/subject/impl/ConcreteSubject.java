package com.chengw.designPattern.observerPattern.diy.subject.impl;

import com.chengw.designPattern.observerPattern.diy.observer.Observer;
import com.chengw.designPattern.observerPattern.diy.subject.Subject;

import java.util.Vector;

/**
 * @author chengw
 */
public class ConcreteSubject implements Subject {

    private float temperature;
    private String warningLevel;
    private final Vector<Observer> vector;

    public ConcreteSubject() {
        this.vector = new Vector<>();
    }

    @Override
    public boolean add(Observer observer) {
        return vector.add(observer);
    }

    @Override
    public boolean remove(Observer observer) {

        return vector.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        System.out.print("=====气象部门发布高温" + this.warningLevel +"警报");
        for (Observer observer : vector) {
            observer.update(this);
        }

    }

    @Override
    public void setTemperature(float temperature) {
            this.temperature = temperature;
            invoke();
    }

    private void invoke(){
        if(this.temperature > 35){
            this.warningLevel = "red";
        }else {
            this.warningLevel = "black";
        }
        notifyAllObserver();
    }

    @Override
    public String temperatureReport() {
        return "温度：" + this.temperature;
    }
}
