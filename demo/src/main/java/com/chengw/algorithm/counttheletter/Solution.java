package com.chengw.algorithm.counttheletter;

import java.util.Scanner;

/**
 * 统计指定字母的数量
 *
 * @author chengwei
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(doCalculate(input));
    }

    private static int doCalculate(String input) {
        if (null == input || input.length() == 0) {
            return 0;
        }
        int count = 0;
        for(int i = 0;i < input.length();i++) {
            if ("a".equals(input.substring(i,i+1))) {
                count++;
            }
        }
        return count;
    }
}
