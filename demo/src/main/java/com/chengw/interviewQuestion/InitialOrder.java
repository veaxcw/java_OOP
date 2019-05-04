package com.chengw.interviewQuestion;


/**
 * 初始化顺序
 * 1.父类静态域
 * 2.父类静态代码块
 * 3.子类静态域
 * 4.子类静态代码快
 * 5.父类普通代码块
 * 5.父类构造方法
 * 6.子类普通代码块
 * 7.子类构造方法
 * **/

public class InitialOrder {
   // public static  InitialOrder initialOrder = new InitialOrder();
    static {
        System.out.println("A");
    }
    {
        System.out.println("B");
    }

    public InitialOrder() {
        System.out.println("C");
    }


}


class parents{


    public void Test(){
        InitialOrder I = new InitialOrder();
    }



    public parents() {
        System.out.println("D");
    }
}
