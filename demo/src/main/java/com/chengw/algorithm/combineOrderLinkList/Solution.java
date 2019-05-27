package com.chengw.algorithm.combineOrderLinkList;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author chengw**/
public class Solution {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**合并两个有序链表**/
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode temp = head;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                head.next = l1;
                l1 = l1.next;
            }else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        head.next = l1 == null?l2:l1;

        return temp.next;

    }

    public static ListNode mergeLists(ListNode[] lists) {

        if(lists.length == 0) {
            return null;
        }

        ListNode[] temp = lists;

        while(temp.length > 1){
            ListNode[] rep = new ListNode[temp.length/2 + 1];
            for(int i = 0;i < rep.length;i++){
                ListNode l1 = temp[i];
                ListNode l2 = (i + rep.length)<temp.length?temp[i + rep.length]:null;
                if(i == 0 && temp.length == 2) {
                    l2 = temp[1];
                }
                ListNode listNode = mergeTwoLists(l1,l2);
                rep[i] = listNode;
                if(i == 0 && temp.length == 2) {
                    return rep[0];
                }
            }
            temp = rep;
        }

        return temp[0];
    }



    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] listNodes = new ListNode[]{l1, l2, l3};
        mergeLists(listNodes);



    }



}
