package com.chengw.algorithm.validPalindrome;


/**
 *验证回文字符串
 * @author chengw
 */
public class Solution {

    public static boolean isPalindrome(String s) {

        String a = s.trim().replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        if(a.length() == 0){
            return true;
        }
        char[] chars = a.toLowerCase().toCharArray();

        int i = 0;
        int j = a.length() - 1;

        while(i < j){
            if(a.charAt(i) == a.charAt(j)){
                i++;
                j--;
            }else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        isPalindrome(".");
    }

}
