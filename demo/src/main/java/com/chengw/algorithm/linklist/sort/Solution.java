package com.chengw.algorithm.linklist.sort;

import com.chengw.algorithm.linklist.common.ListNode;

/**
 * @author chengw
 */
//TODO
public class Solution {

    /**
     * 冒泡排序
     * ***/
    public static ListNode sortList(ListNode head) {


        ListNode cur = head;
        ListNode tail = null;
        while (cur != tail){
            while (cur.next != tail){
                if(cur.val > cur.next.val){
                    int temp = cur.next.val;
                    cur.next.val = cur.val;
                    cur.val = temp;
                }
                cur = cur.next;
            }
            tail = cur;
            cur = head;
        }


        return  head;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        sortList(head);

    }

}
