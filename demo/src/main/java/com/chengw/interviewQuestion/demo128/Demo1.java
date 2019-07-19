package com.chengw.interviewQuestion.demo128;

/**
 * @author chengw
 */
public class Demo1 {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        int d = 1;
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(a.hashCode());
        System.out.println(d);


    }
}
