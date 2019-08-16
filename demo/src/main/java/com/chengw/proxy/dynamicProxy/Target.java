package com.chengw.proxy.dynamicProxy;

import com.chengw.proxy.dynamicProxy.Operation;

public class Target implements Operation {

    @Override
    public void say() {
        System.out.println(" f**king the word!");
    }

}
