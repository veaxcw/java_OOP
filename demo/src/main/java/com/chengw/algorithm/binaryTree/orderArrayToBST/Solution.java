package com.chengw.algorithm.binaryTree.orderArrayToBST;

import com.chengw.algorithm.binaryTree.common.TreeNode;
import com.chengw.array.Array;

import java.util.Arrays;

/**
 * @author chengw
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 */
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }

        return buildTree(nums,0,nums.length - 1);

    }

    private TreeNode buildTree(int[]nums, int start,int end){
        if(start > end) {
            return null;
        }

        int mid = (start + end )/2;
        TreeNode pNode = new TreeNode(nums[mid]);
        pNode.left = buildTree(nums,start,mid - 1);
        pNode.right = buildTree(nums,mid + 1, end);

        return pNode;


    }
}
