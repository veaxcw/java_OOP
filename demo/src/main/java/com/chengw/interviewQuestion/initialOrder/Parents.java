package com.chengw.interviewQuestion.initialOrder;

/**
 * @author veax
 */
public class Parents{

    static{
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类普通代码块");
    }


    public Parents() {
        System.out.println("父类构造方法");
    }
}
