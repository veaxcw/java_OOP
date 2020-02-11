package com.chengw.algorithm.hindex;


import java.util.*;

/**
 * @author chengwei
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Integer> in = new ArrayList<>();

        String[] strings = input.split(",");

        for (int i = 0; i < strings.length; i++) {
            in.add(Integer.valueOf(strings[i]));
        }
    }

    private static int doCalculate(int[] citations) {

        Arrays.sort(citations);
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            h = citations.length - i;
            if (h < citations[i]) {
                return h;
            }
        }

        return h;

    }

}
