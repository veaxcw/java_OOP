package com.chengw.algorithm.linklist.convertLinkList;

import com.chengw.algorithm.linklist.common.ListNode;

import java.util.Arrays;

/**
 * 反转链表
 * @author chengw
 */
public class Solution {
    /**
     * 傻大笨粗解法
     * **/
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        int[] array = new int[10];

        int length = 0;
        while(head != null){
            array[length++] = head.val;
            head = head.next;
            if( head != null && length >= array.length){
                array = Arrays.copyOf(array,array.length + 10);
            }
        }
        ListNode result = new ListNode(0);
        head = result;
        while (length-- > 0){
            result.next = new ListNode(array[length]);
            result = result.next;
        }

        return head.next;
    }
    /**
     * 迭代解法
     * **/
    public ListNode reverseList(ListNode head, int a){
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;

    }



    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        reverseList(head);
    }

}
