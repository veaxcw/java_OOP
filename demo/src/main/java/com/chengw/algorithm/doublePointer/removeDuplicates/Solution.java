package com.chengw.algorithm.doublePointer.removeDuplicates;

/**给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

        不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。*/

/**
 * @author chengw
 */
public class Solution {
    /**
     * 双指针法：
     * 算法
     *
     * 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。只要 nums[i] = nums[j]，我们就增加 j 以跳过重复项。
     * 当我们遇到 nums[j] ！= nums[i]
     * =nums[i] 时，跳过重复项的运行已经结束，因此我们必须把它（nums[j]）的值复制到 nums[i + 1]。然后递增 i，接着我们将再次重复相同的过程，
     * 直到 j 到达数组的末尾为止。
     * ***/
    public int removeDuplicates(int[] nums) {


        int i = 0;
        for(int j = 1;j < nums.length;j++){
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }

        return i+1;

    }

    /**
     * 每个元素可以出现两次
     * ***/
    public int removeDuplicate(int[] nums) {


        int i = 0;
        for(int j = 1;j < nums.length;j++){
            if(nums[j] != nums[i] || i < 2){
                i++;
                nums[i] = nums[j];
            }
        }

        return i;

    }


}
