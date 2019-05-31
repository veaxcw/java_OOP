package com.chengw.designPattern.singletonPattern;


/**单例模式
 * @author chengw**/
public class SingletonHungry {

    /***
     * 饿汉式单例模式
     *
     * 类在加载到内存的时候  就已经创建对象流
     * **/
    public static final SingletonHungry INSTANCE = new SingletonHungry();

    private SingletonHungry() {
    }

    public static synchronized SingletonHungry getIntance(){
        return INSTANCE;
    }
}
