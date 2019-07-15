package com.chengw.algorithm.stack.validbracket;

import java.util.Stack;

/**
 * 有效括号 验证
 * @author chengw
 */
public class Solution {

    public boolean isValid(String s) {
        Stack stack = new Stack();
        for(int i = 0;i < s.length();i++){
            //if(stack.contains())
                //todo
            stack.push(s.charAt(i));

        }
        return true;
    }

}
