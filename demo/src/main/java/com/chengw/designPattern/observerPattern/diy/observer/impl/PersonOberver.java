package com.chengw.designPattern.observerPattern.diy.observer.impl;

import com.chengw.designPattern.observerPattern.diy.observer.Observer;
import com.chengw.designPattern.observerPattern.diy.subject.Subject;

/**
 * @author chengw
 */
public class PersonOberver implements Observer {
    @Override
    public void update(Subject subject) {
        System.out.print("个人收到预警：" + subject.temperatureReport());
    }
}
