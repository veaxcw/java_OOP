package com.chengw.designPattern.observerPattern;

/**
 * @author chengw
 */
public interface IObserver {


    /**
     * 监听subject
     * @param subject 监听对象
     */
    void update(ISubject subject);

}
