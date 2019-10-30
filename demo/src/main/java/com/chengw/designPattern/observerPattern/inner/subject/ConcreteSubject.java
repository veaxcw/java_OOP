package com.chengw.designPattern.observerPattern.inner.subject;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * 继承了可观察者
 * @author chengw
 */
@Data
public class ConcreteSubject extends Observable {

    private float temperature;
    private String warningLevel;
    private final Vector<Observer> vector;

    public ConcreteSubject() {
        this.vector = new Vector<>();
    }


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
        setChanged();
        notifyObservers();
    }

    public String temperatureReport() {
        return "温度：" + this.temperature;
    }
}
