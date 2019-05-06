package com.chengw.algorithm.sumOfTwoNumbers;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * **/

/**解决思路：
 * 暴力方法
 * 遍历哈希表
 * 利用key value对来完成
 * **/
public class Solution {

    public int[] twoSum(int[] nums,int target){

        Map<Integer,Integer> integers = new HashMap<>();

        for(int i = 0;i < nums.length;i++){
            if(integers.containsKey(target - nums[i]))
                return new int[]{integers.get(target-nums[i]),i};
            integers.put(nums[i],i);
        }

       //todo

        return null;
    }

    public static void main(String[] args) {
        int[] demo = new int[]{3,3};
        int[] result = new Solution().twoSum(demo,6);
        System.out.println(6 + "=" + result[0] + "+" + result[1]);
    }
}
