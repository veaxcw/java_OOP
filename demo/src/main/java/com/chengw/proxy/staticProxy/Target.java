package com.chengw.proxy.staticProxy;

public class Target implements Operation {

    @Override
    public void say() {
        System.out.println(" f**king the word!");
    }

}
