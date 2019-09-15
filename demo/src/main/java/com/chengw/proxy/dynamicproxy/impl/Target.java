package com.chengw.proxy.dynamicproxy.impl;

import com.chengw.proxy.dynamicproxy.Operation;

/**
 * @author veax
 */
public class Target implements Operation {



    public Target() {
    }

    @Override
    public void say() {
        System.out.println(" f**king the word!");
    }

}
