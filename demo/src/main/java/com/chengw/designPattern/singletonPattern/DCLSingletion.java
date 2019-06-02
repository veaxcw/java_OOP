package com.chengw.designPattern.singletonPattern;


/**
 * 双重检查锁定,来减少开销
 * 使用volatile 保障了可见性和有序性
 * **/
public class DCLSingletion {

    private static volatile DCLSingletion instance;

    private DCLSingletion(){}

    public static DCLSingletion getInstance(){
        if(null == instance){
            synchronized (DCLSingletion.class){
                if(null == instance){
                    instance = new DCLSingletion();
                }
            }
        }

        return instance;
    }

}
