package com.chengw.algorithm.bianryTreeInOrderTraversal;

import com.chengw.dataStructure.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中序遍历
 * **/
public class Solution {
    class TreeNode implements Comparable<TreeNode> {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public int compareTo(TreeNode o) {
            return 0;
        }
    }

    List<Integer> result = new ArrayList<>();

    /**
     * 递归实现
     * **/
    public List<Integer> inorderTraversal(TreeNode root) {
            if(root != null){
                inorderTraversal(root.left);
                result.add(root.val);
                inorderTraversal(root.right);
            }
            return result;
    }
    /**
     * 非递归实现
     * **/
    public List<Integer> inorderTraversal(TreeNode root,int a) {
        List<Integer> list = new ArrayList<>();

        TreeNode t = root;

        Stack<TreeNode> stack = new Stack<>();

        while(t != null || stack.size() == 0){
            while(t != null){
                stack.push(t);
                t = t.left;
            }
            if(stack.size() != 0){
                list.add(stack.pop().val);
                t = t.right;
            }
        }
        return list;
    }




}
