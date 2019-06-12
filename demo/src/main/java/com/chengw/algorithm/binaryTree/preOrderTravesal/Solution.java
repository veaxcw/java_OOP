package com.chengw.algorithm.binaryTree.preOrderTravesal;

import com.chengw.algorithm.binaryTree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chengw
 */
public class Solution {

    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {

        if(root != null){
            result.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }

        return result;

    }

}
