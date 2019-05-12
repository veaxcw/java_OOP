package com.chengw.algorithm.regex;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * **/
public class Solution {

    public static boolean isMatch(String s, String p) {

        int sLen = s.length();
        int pLen = p.length();

        /***
         * 该二维数组存储每个字符是否匹配的情况
         * */
        boolean[] memo = new boolean[sLen + 1];

        memo[0] = true;

        boolean star ;


        for(int i = 1, j = 1; i <= sLen && j <= pLen; i++){
            star = j <= pLen && p.charAt(j - 1) == '*';
            if(!star) j++;
            if(star){
               int index = p.lastIndexOf("*");
                memo[i] = memo[i -1] && ( isContain(p.substring(0,index),s.charAt(i - 1)) || p.charAt(i - 2 ) == '.');
            }else {
                memo[i] = i < pLen && memo[i - 1] && (s.charAt(i - 1) == p.charAt(i - 1) || p.charAt(i -1 ) == '.');
            }
        }


        return  memo[sLen];

    }

    private static boolean isContain(String s,char c){

        Boolean[] flag = new Boolean[s.length() + 1];

        flag[0] = false;

        for(int i = 1;i <= s.length();i++){
            flag[i]  = flag[i - 1] || s.charAt(i - 1) == c;
        }

        return flag[s.length()];
    }

    public static void main(String[] args) {
        System.out.println( isMatch("aa","a"));
    }

}
