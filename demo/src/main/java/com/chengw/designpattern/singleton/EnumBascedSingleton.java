package com.chengw.designpattern.singleton;

/**
 * 基于枚举的单例模式
 * */
public class EnumBascedSingleton {

    public static enum Singleton{

        INSTANCE;

        private Singleton(){}

    }

}
