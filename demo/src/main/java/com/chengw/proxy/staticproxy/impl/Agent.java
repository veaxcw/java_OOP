package com.chengw.proxy.staticproxy.impl;

import com.chengw.proxy.dynamicproxy.Operation;

public class Agent implements Operation {

    private Operation target;

    public Agent(Operation target) {
        this.target = target;
    }

    @Override
    public void say() {
        System.out.println("Hello World");
        target.say();
        System.out.println(" GUN ");
    }
}
