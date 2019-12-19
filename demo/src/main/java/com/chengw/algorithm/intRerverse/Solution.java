package com.chengw.algorithm.intRerverse;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * */

public class Solution {


    /**利用队列的先进先出**/
    public static int reverse(int x) {

        long ans = 0;


        int temp = Math.abs(x);

        Queue<Integer> queue = new ArrayDeque<>();

        while(temp != 0){
            int position = temp%10;
            queue.add(position);
            temp = temp/10;
        }

        while(queue.size() != 0){
            ans = (long) (ans + queue.poll() * Math.pow(10,queue.size()));
            if(ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE)
                return  0;
        }

        return x < 0? (int)-ans: (int) ans;

    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }



}
