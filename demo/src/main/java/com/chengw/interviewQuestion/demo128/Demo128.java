package com.chengw.interviewQuestion.demo128;

/**
 * 在常量池中 会对-128～127之间的数字进行缓存
 *
 * @author veax
 */
public class Demo128 {

    public static void main(String[] args) {

        Integer i = 128;
        Integer j = 128;
        System.out.println(i == j);

        Integer m = 127;
        Integer n =127;
        System.out.println(m == n);
    }

}
