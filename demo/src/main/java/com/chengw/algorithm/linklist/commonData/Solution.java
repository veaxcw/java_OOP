package com.chengw.algorithm.linklist.commonData;

import com.chengw.algorithm.linklist.common.ListNode;


/**
 * @author chengw 这尼玛根本不是我想出来的
 */
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        ListNode l1 = headA;
        ListNode l2 = headB;

        while (l1 != l2 ){
            l1 = l1==null?headB:l1.next;
            l2 = l2==null?headB:l2.next;
        }

        return l1;
    }

}
