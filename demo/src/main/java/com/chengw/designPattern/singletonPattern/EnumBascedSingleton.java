package com.chengw.designPattern.singletonPattern;

/**
 * 基于枚举的单例模式
 * */
public class EnumBascedSingleton {

    public static enum Singleton{

        INSTANCE;

        private Singleton(){}

    }

}
