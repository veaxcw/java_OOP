package com.chengw.algorithm.binarySum;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 */
public class Solution {

    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int i = 1;
        int oldTemp = 0;
        while(i <= Integer.max(a.length(),b.length()) ){
            int aTemp = i <= a.length()?Integer.parseInt(String.valueOf(a.charAt(a.length() - i))):0;
            int bTemp =  i <= b.length()?Integer.parseInt(String.valueOf(b.charAt(b.length() - i))):0;
            int newTemp = (aTemp + bTemp + oldTemp) > 1?1:0;
            stack.push((aTemp + bTemp + oldTemp)%2);
            oldTemp = newTemp;
            i++;
        }
        if(oldTemp == 1)
            stack.push(1);
        while (!stack.isEmpty()){
            result.append(stack.pop().toString());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        addBinary("1111","1111");
    }

}
