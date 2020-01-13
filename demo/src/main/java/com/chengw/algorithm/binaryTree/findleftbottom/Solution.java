
package com.chengw.algorithm.binaryTree.findleftbottom;

import com.chengw.algorithm.binaryTree.common.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author chengwei
 */
public class Solution {


    int max = -1;

    int res = 0;

    Map<Integer, List<Integer>> map = new HashMap<>();


    public int findBottomLeftValue(TreeNode root) {
         preOrderTraversal(root,0);
         return res;

    }

    public Integer preOrderTraversal(TreeNode root,int depth) {

        if (null == root) {
            return null;
        }

        if (root != null) {

            if (depth > max) {
                max = depth;
                res = root.val;
            }
            preOrderTraversal(root.left,++depth);
            preOrderTraversal(root.right,++depth);
        }

        return 0;


    }

}
