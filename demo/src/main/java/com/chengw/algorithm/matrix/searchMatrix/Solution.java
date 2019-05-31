package com.chengw.algorithm.matrix.searchMatrix;


/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        boolean flag = false;

        for(int i = 0;i < matrix.length;i++)
            for(int j = 0;j < matrix[i].length;j++){
                if(matrix[i][j] == target){
                    flag = true;
                    break;
                }

            }

        return flag;


    }

}
