package com.chengw.proxy.staticProxy;

import com.chengw.proxy.dynamicProxy.Operation;

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
