package com.chengw.designPattern.observerPattern;

/**
 * @author chengw
 */
public interface ISubject {

    /**
     * 增加观察者
     * @param observer
     * @return
     */
     boolean add(IObserver observer);

    /**
     * 删除观察者
     * @param observer
     * @return
     */
     boolean remove(IObserver observer);


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
