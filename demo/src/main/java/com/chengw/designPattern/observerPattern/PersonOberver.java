package com.chengw.designPattern.observerPattern;

/**
 * @author chengw
 */
public class PersonOberver implements IObserver {
    @Override
    public void update(ISubject subject) {
        System.out.print("个人收到预警：" + subject.temperatureReport());
    }
}
