package com.chengw.algorithm.dichotomy.findLocation;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * ***/

/**
 * @author chengw
 */
public class Solution {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid  = left + (right - left)/2;
            if(nums[mid] == target){
                left = mid;
                right = mid;
                while(left > 0 && nums[left - 1] == nums[left]) {
                    left--;
                }
                while (right < nums.length - 1 && nums[right + 1] == nums[right]){
                    right++;
                }
                result[0] = left;
                result[1] = right;
                break;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }

        }

        return result;
    }

    public static void main(String[] args) {
         searchRange(new int[]{1},1);
    }

}
