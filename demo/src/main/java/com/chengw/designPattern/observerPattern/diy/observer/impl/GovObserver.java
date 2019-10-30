package com.chengw.designPattern.observerPattern.diy.observer.impl;

import com.chengw.designPattern.observerPattern.diy.observer.Observer;
import com.chengw.designPattern.observerPattern.diy.subject.Subject;

/**
 * @author chengw
 */
public class GovObserver implements Observer {
    @Override
    public void update(Subject subject) {
        System.out.println("政府部门收到高温预警：" + subject.temperatureReport());
    }
}

