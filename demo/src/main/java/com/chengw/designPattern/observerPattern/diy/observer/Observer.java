package com.chengw.designPattern.observerPattern.diy.observer;

import com.chengw.designPattern.observerPattern.diy.subject.Subject;

/**
 * @author chengw
 */
public interface Observer {


    /**
     * 监听subject
     * @param subject 监听对象
     */
    void update(Subject subject);

}
