package com.chengw.designPattern.singletonPattern;

/**
 * 基于竟态内部类的单例模式实现
 * */
public class StaticHolderSingleton {


    private StaticHolderSingleton(){}

    private static class InstanceHolder{

        final static StaticHolderSingleton  INSTANCE = new StaticHolderSingleton();

    }

    public static StaticHolderSingleton getInstance(){

        return InstanceHolder.INSTANCE;
    }

}
