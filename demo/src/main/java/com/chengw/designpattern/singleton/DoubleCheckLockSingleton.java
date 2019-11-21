package com.chengw.designpattern.singleton;


/**
 * 双重检查锁定,来减少开销
 * 使用volatile 保障了可见性和有序性
 *
 * @author chengw**/
public class DoubleCheckLockSingleton {

    private static volatile DoubleCheckLockSingleton instance;

    private DoubleCheckLockSingleton(){}

    public static DoubleCheckLockSingleton getInstance(){
        if(null == instance){
            synchronized (DoubleCheckLockSingleton.class){
                if(null == instance){
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }

        return instance;
    }

}
