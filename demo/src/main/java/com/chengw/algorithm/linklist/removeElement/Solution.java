package com.chengw.algorithm.linklist.removeElement;

import com.chengw.algorithm.linklist.common.ListNode;

/**
 * 移除链表中的元素
 * @author chengw
 */
public class Solution {

    public static ListNode removeElements(ListNode head, int val) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode newHead = pre.next;

       while (pre.next != null){
           if(pre.next.val == val){
               if(pre.next == newHead){
                   newHead = pre.next.next;
                   continue;
               }
               pre.next = pre.next.next;
               continue;
           }
           pre = pre.next;
       }
        return newHead;
    }

    public static void main(String[] args) {
        removeElements(new ListNode(2),2);
    }

}
