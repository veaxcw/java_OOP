package com.chengw.algorithm.unzip;

import java.util.Scanner;

/**
 * 华为面试题
 *
 * 有一种简易压缩算法：针对由全部小写字母组成的字符串，将其中连续超过两个相同字目的部分压缩成连续个数加该字母，其他部分保持原样不变。
 * 例如，字符串：aaabccccd 经过压缩成为字符串：3ab4cd。请您编写一个unZip函数，根据输入的字符串，判断其是否为合法压缩过的字符串。
 * 若输入合法，则输出解压后的字符串，否则输出：!error 来报告错误。
 *
 * 测试：3ab4cd合法，aa4b合法,caa4b合法,3aa4b不合法,22aa不合法,2a4b不合法,22a合法
 * @author chengwei
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(unzip(input));

    }

    private static String unzip(String input) {
        if (null == input || "".equals(input)) {
            return "";
        }
        if(!isIllegal(input)) {
            return "!error";
        }
        StringBuilder var = new StringBuilder();
        for(int i = 0;i < input.length();i++) {
            StringBuilder num = new StringBuilder();
            while(isNumber(input.charAt(i))) {
                num.append(input.charAt(i));
                i++;
            }
            int size = "".equals(num.toString()) ? 1 : Integer.parseInt(num.toString());
            for(int j = 0; j < size;j++) {
                var.append(input.charAt(i));
            }
        }
        return var.toString();
    }

    private static Boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


    private static Boolean isIllegal(String str) {
        // todo
        int repeat = 1;
        char last = '0';
        for (int i = 0; i < str.length();i++) {
            if (repeat > 2) {
                return false;
            }
            StringBuilder num = new StringBuilder();
            while(isNumber(str.charAt(i))) {
                num.append(str.charAt(i));
                i++;
            }
            int size = "".equals(num.toString()) ? 1 : Integer.parseInt(num.toString());
            if (size == 1 && str.charAt(i) == last) {
                repeat++;
                continue;
            }
            if (size == 2) {
                return false;
            }
            if(size > 2 && i < str.length() - 1 && str.charAt(i+1) == str.charAt(i)) {
                return false;
            }
            repeat = 1;
            last = str.charAt(i);
        }
        return true;
    }

}
