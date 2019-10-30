package com.chengw.designPattern.observerPattern.inner.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author chengw
 */
public class PersonObserver implements Observer {
    /**
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.print("个人收到高温预警：" +  arg.toString());
    }
}
