package com.chengw.algorithm.singleNum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengw
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 */
public class Solution {



    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i <nums.length;i++){
            result ^= nums[i];
        }

        return result;
    }

    public static int[] singleNumber(int[] nums,int a) {

        List<Integer> list = new ArrayList<>();

        Map<Integer,Integer> map = new HashMap<>();
        Integer count = null;
        for(int i = 0;i < nums.length;i++){
            count = map.get(nums[i]);
            count = count==null?1:++count;
            map.put(nums[i],count);
        }
        for(Integer i:map.keySet()){
            if( (map.get(i) == 1)){
                list.add(i);
            }
        }

        int[] result = new int[list.size()];

        for(int i = 0;i < result.length;i++){
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        singleNumber(new int[]{1,2,1,3,2,5},0);
    }

}
