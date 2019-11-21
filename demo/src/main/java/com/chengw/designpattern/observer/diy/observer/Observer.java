package com.chengw.designpattern.observer.diy.observer;

import com.chengw.designpattern.observer.diy.subject.Subject;

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
