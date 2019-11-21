package com.chengw.designpattern.observer.diy.observer.impl;

import com.chengw.designpattern.observer.diy.observer.Observer;
import com.chengw.designpattern.observer.diy.subject.Subject;

/**
 * @author chengw
 */
public class PersonOberver implements Observer {
    @Override
    public void update(Subject subject) {
        System.out.print("个人收到预警：" + subject.temperatureReport());
    }
}
