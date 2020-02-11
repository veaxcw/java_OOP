package com.chengw.algorithm.binarytree.levelOrderTravesal;

import com.chengw.algorithm.binarytree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * **/
public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        levelOrderHelper(root, 0, ans);

        return ans;
    }

    private void levelOrderHelper(TreeNode root, int depth, List<List<Integer>> ans) {
        if (root == null)
            return;
        // 如果采用中序/后序遍历，需要将if改成while
        if (depth >= ans.size())
            ans.add(new ArrayList<>());

        ans.get(depth).add(root.val);

        levelOrderHelper(root.left, depth + 1, ans);
        levelOrderHelper(root.right, depth + 1, ans);
    }



}
