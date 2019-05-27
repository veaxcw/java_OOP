package com.chengw.algorithm.longestCommonPrefix;

/**
*编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z
 *
 * @author chengw*/
public class Solution {

    /**
     * 水平扫描法
     * 思路
     *
     * 首先，我们将描述一种查找一组字符串的最长公共前缀LCP(S1S2S3....)的简单方法。 我们将会用到这样的结论：
     * LCP(S1......Sn) = LCP(LCP(LCP(LCP(S1，S2)S3)S4)......Sn)

     * 算法
     *
     * 为了运用这种思想，算法要依次遍历字符串 ，当遍历到第 i 个字符串的时候，找到最长公共前缀
     * */
    public static String longestCommonPrefix(String[] strs) {

        if(strs.length == 0) {
            return "";
        }

        String prefix = strs[0];

        for(int i = 0;i < strs.length;i++){
            /**
            * 返回第一次出现指定字符串在此字符创中的索引
             * ***/
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0,prefix.length() - 1 );
            }
            if(prefix.isEmpty()) {
                return "";
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[4];
        strs[0] = "leets";
        strs[1] = "leetcode";
        strs[2] = "leet";
        strs[3] = "leeds";
        System.out.println(longestCommonPrefix(strs));
    }
}
