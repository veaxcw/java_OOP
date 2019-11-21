package com.chengw.designpattern.singleton;

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

    /**
     * 该方法保证了线程安全，但是却增加了开销，因为每次执行getInstance都必须要申请锁
     * **/
    public static synchronized SingletonLazy getInstance(){
        if(null == INSTANCE) {
            INSTANCE = new SingletonLazy();
        }

        return INSTANCE;
    }


}
