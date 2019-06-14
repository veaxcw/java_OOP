package com.chengw.designPattern.observerPattern;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author chengw
 */
public class Subject implements ISubject {

    private float temperature;
    private String warningLevel;
    private final Vector<IObserver> vector;

    public Subject() {
        this.vector = new Vector<>();
    }

    @Override
    public boolean add(IObserver observer) {
        return vector.add(observer);
    }

    @Override
    public boolean remove(IObserver observer) {

        return vector.remove(vector);
    }

    @Override
    public void notifyAllObserver() {
        System.out.print("=====气象部门发布高温" + this.warningLevel +"警报");
        Iterator<IObserver> iterator = vector.iterator();
        while(iterator.hasNext()){
            IObserver observer = iterator.next();
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
