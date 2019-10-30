package com.chengw.designPattern.observerPattern.inner.observer;


import java.util.Observable;
import java.util.Observer;

/**
 * @author chengw
 */
public class GovObserver implements Observer {

    /**
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.print("政府部门收到高温预警：" +  arg.toString());
    }
}

