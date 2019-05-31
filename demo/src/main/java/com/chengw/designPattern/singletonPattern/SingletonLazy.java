package com.chengw.designPattern.singletonPattern;

/**
 * @author chengw
 */
public class SingletonLazy {


    /***
     * 懒汉式单例模式
     *
     * 类在第一次使用时才创建对象
     * **/
    private static  SingletonLazy INSTANCE ;

    private SingletonLazy() {
    }

    public static synchronized SingletonLazy getInstance(){
        if(null == INSTANCE) {
            INSTANCE = new SingletonLazy();
        }

        return INSTANCE;
    }

}
