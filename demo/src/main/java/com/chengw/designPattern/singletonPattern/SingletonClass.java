package com.chengw.designPattern.singletonPattern;


/**单例模式**/
public class SingletonClass {

    public static final SingletonClass INSTANCE = new SingletonClass();

    private SingletonClass() {
    }

    public static synchronized SingletonClass getIntance(){
        return INSTANCE;
    }
}
