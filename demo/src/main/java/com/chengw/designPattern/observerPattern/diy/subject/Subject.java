package com.chengw.designPattern.observerPattern.diy.subject;

import com.chengw.designPattern.observerPattern.diy.observer.Observer;

/**
 * @author chengw
 */
public interface Subject {

    /**
     * 增加观察者
     * @param observer
     * @return
     */
     boolean add(Observer observer);

    /**
     * 删除观察者
     * @param observer
     * @return
     */
     boolean remove(Observer observer);


    /**
     * 通知所有观察者
     */
     void notifyAllObserver();

    /**
     * 设置温度值
     *
     * @param temperature
     */
     void setTemperature(float temperature);
    /**
     * 获得温度预警
     *
     * @return
     */
     String temperatureReport();



}
