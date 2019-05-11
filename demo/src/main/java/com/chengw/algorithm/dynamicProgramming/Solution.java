package com.chengw.algorithm.dynamicProgramming;

/****
 *
 * Those who cannot remember the past condemned to repeat id;
 *
 * 动态规划核心：
 *
 * A * "1+1+1+1+1+1+1+1 =？" *
 *
 * A : "上面等式的值是多少"
 * B : *计算* "8!"
 *
 * A *在上面等式的左边写上 "1+" *
 * A : "此时等式的值为多少"
 * B : *quickly* "9!"
 * A : "你怎么这么快就知道答案了"
 * A : "只要在8的基础上加1就行了"
 * A : "所以你不用重新计算因为你记住了第一个等式的值为8!动态规划算法也可以说是 '记住求过的解来节省时间'"
 *
 */

public class Solution {

    //递归求解斐波那契数列
    //问题：很多结点被计算了多次
    public static int fibonacci(int n){
        if(n <= 0)
            return 0;
        if(n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci((Integer) 6));
        System.out.println(fib(6));
    }

    //自顶向下的备忘录法
    //仍然需要递归
    public static int fibonacci(Integer n){
        if(n <= 0)
            return n;
        int[] memo = new int[n+1];
        for(int i = 0;i <= n;i++)
            memo[i] = -1;
        return fib(n,memo);
    }

    public static int fib(int n,int[] memo){

        if(memo[n] != -1)
            return memo[n];
        if(n <= 2)
            memo[n] = 1;
        else
            memo[n] = fib(n-1,memo) + fib(n-2,memo);
        return memo[n];

    }

    //自底向上
    public static int fib(int n){
        if(n <= 0)
            return n;
        int[] memo = new int[n + 1];

        memo[0] = 0;
        memo[1] = 1;
        for(int i = 2;i <= n; i++){
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }




}
