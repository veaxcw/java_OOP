package com.chengw.algorithm.sumOfTwoNumbers;

import java.util.HashMap;

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
public class Solution {

    public int[] twoSum(int[] nums,int target){

        HashMap<Integer,Integer> ints = new HashMap<>();
        for(int i = 0;i < nums.length; i++)
            ints.put(nums[i],i);

        /**先排个序**/
        for(int i = 0;i < nums.length;i++){
            for(int j = 0;j < nums.length - i - 1;j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }

        for(int i = 0; i < nums.length;i++){
            if(nums[i] > target)
                continue;
            else {
                int sub = target - nums[i];
                if(sub < target/2){
                    for(int j = 0;j < i;j++){
                        if(sub == nums[j])
                            return new int[]{ints.get(nums[i]),ints.get(nums[j])};
                    }
                }else {
                    for(int j = i; j < nums.length; j++){
                        if(sub == nums[j])
                            return new int[]{ints.get(nums[i]),ints.get(nums[j])};
                    }
                }
            }
        }


        return null;
    }

    public static void main(String[] args) {
        int[] demo = new int[]{3,3};
        int[] result = new Solution().twoSum(demo,6);
        System.out.println(6 + "=" + result[0] + "+" + result[1]);
    }
}
