package com.chengw.algorithm.linklist.palindrome;

import com.chengw.algorithm.linklist.common.ListNode;

/**
 * 回文链表
 * 要求时间复杂度O（n）,空间复杂度O（1）
 * @author chengw
 */
public class Solution {

    public static boolean  isPalindrome(ListNode head) {

        if(head == null || head.next == null) {
            return  true;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        ListNode prepare = null;

        while(fast != null&&fast.next != null){

            pre = slow;
            slow = slow.next;
            fast = fast.next.next;

            //翻转链表
            pre.next = prepare;
            prepare = pre;
        }

        ListNode p2 = slow.next;
        slow.next = pre;
        ListNode p1 = fast == null?slow.next:slow;
        while(p1 != null){
            if(p1.val == p2.val){
                p1 = p1.next;
                p2 = p2.next;
                continue;
            }
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);

        isPalindrome(head);
    }
}
