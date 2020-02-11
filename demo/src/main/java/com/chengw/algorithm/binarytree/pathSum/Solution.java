package com.chengw.algorithm.binarytree.pathSum;

import com.chengw.algorithm.binarytree.common.TreeNode;

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

    private static List<Integer> suma = new ArrayList<>();

    public  int pathSum(TreeNode root, int sum) {

        if(root == null){
            return 0;
        }

        return cal(root,sum) + pathSum(root.left,sum) + pathSum(root.right,sum);

    }

    private  int cal(TreeNode root,int sum){

        if(root == null){
            return 0;
        }
        sum -= root.val;
        return (sum == 0?1:0) + cal(root.left,sum) + cal(root.right,sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        root.left.right.right = new TreeNode(1);

        root.right.right = new TreeNode(11);

        //pathSum(root,8);



        //todo



    }

}
