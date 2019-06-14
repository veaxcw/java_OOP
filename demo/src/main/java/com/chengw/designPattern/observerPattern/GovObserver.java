package com.chengw.designPattern.observerPattern;

/**
 * @author chengw
 */
public class GovObserver implements IObserver {
    @Override
    public void update(ISubject subject) {
        System.out.println("政府部门收到高温预警：" + subject.temperatureReport());
    }
}

