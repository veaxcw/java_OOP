package com.chengw.algorithm.addStrings;

import java.util.Stack;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

 * @author chengw
 */
public class Solution {
    public static String addStrings(String num1, String num2) {

        StringBuilder result = new StringBuilder();

        int i = 1;
        int oldTemp = 0;
        int aTemp,bTemp,newTemp,temp;
        while(i <= Integer.max(num1.length(),num2.length()) ){
             aTemp = i <= num1.length()?num1.charAt(num1.length() - i) - 48:0;
             bTemp =  i <= num2.length()?num2.charAt(num2.length() - i) - 48:0;
             temp = aTemp + bTemp + oldTemp;
             newTemp = temp >= 10?temp/10:0;
             result.append(temp % 10);
            oldTemp = newTemp;
            i++;
        }
        if(oldTemp != 0) {
            result.append(oldTemp);
        }
        return result.reverse().toString();

    }

    public static void main(String[] args) {
        addStrings("11","9");
    }
}

