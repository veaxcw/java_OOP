package com.chengw.algorithm.palindromeNumber;

import java.util.ArrayList;
import java.util.List;

/***
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * **/
public class Solution {
    public static boolean isPalindrome(int x) {

        boolean flag = false;

        if(x < 0 )
            return false;
        List<Integer> list = new ArrayList<>();

        int temp = x;

        while(temp != 0){
            list.add(temp%10);
            temp = temp/10;
        }
        int i = 0;
        int j = list.size() - 1;
        while(i <= j){
            if(list.get(i) == list.get(j)){
                i++;
                j--;
                flag = true;
            }
            else
                return false;
        }
        return x < 0?false:(x == 0?true:flag);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(0));
    }

}
