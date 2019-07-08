package com.chengw.algorithm.binaryTree.pathSum;

import com.chengw.algorithm.binaryTree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数
 *
 * @author chengw
 */
public class Solution {

    //todo

    private List<Integer> suma = new ArrayList<>();

    public int pathSum(TreeNode root, int sum) {

        inOrder(root);

        int r = 0;
        for(Integer i : suma){
            if( i == sum){
                r++;
            }
        }

        return r;

    }

    private void inOrder(TreeNode root){
        if(root != null){
            inOrder(root.left);
            for(int i = 0;i < suma.size();i++){
                suma.set(i,(suma.get(i) + root.val));
            }
            suma.add(root.val);
            inOrder(root.right);
        }


    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        //todo



    }

}
