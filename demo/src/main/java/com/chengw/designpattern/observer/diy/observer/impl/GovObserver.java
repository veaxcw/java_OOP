package com.chengw.designpattern.observer.diy.observer.impl;

import com.chengw.designpattern.observer.diy.observer.Observer;
import com.chengw.designpattern.observer.diy.subject.Subject;

/**
 * @author chengw
 */
public class GovObserver implements Observer {
    @Override
    public void update(Subject subject) {
        System.out.println("政府部门收到高温预警：" + subject.temperatureReport());
    }
}

