package com.chengw.algorithm.linklist.insertSort;

import com.chengw.algorithm.linklist.common.ListNode;

/**
 * 对链表进行插入排序
 * @author chengw
 */
public class Solution {

    //todo todo todo

    public ListNode insertionSortList(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }
        /**
         * 指向未排序部分
         * **/
       ListNode exthead = new ListNode(0);
       ListNode cur = head;
       ListNode temp = exthead;

       while(cur != null){
           while(temp.next != null && temp.next.val > cur.val){
               temp = temp.next;
           }
           temp.next = cur;
           cur = cur.next;
           temp = exthead;
       }

       return exthead.next;

    }



    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        new Solution().insertionSortList(head);
    }

}
