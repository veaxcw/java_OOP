
package com.chengw.algorithm.binarytree.findleftbottom;

import com.chengw.algorithm.binarytree.common.TreeNode;


/**
 *
 * 找到二叉树的最左值
 *
 * @author chengwei
 */
public class Solution {

    int max = -1;

    int res = 0;

    public int findBottomLeftValue(TreeNode root) {
         preOrderTraversal(root,0);
         return res;

    }

    public void preOrderTraversal(TreeNode root, int depth) {

        if (null == root) {
            return;
        }
        if (depth > max) {
            max = depth;
            res = root.val;
        }
        preOrderTraversal(root.left,++depth);
        preOrderTraversal(root.right,++depth);
    }



}
