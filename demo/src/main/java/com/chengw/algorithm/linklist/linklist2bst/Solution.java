package com.chengw.algorithm.linklist.linklist2bst;

import com.chengw.algorithm.linklist.common.ListNode;
import com.chengw.algorithm.linklist.common.TreeNode;

/**
 * 排序好的链表转高度平衡二叉树
 * @author chengw
 */
public class Solution {

    public static TreeNode sortedList2BST(ListNode head){

        return sortedList(head,null);

    }

    //todo todo todo


    public static TreeNode sortedList(ListNode head,ListNode tail){
        if(head == null ) {
            return null;
        }

        if( head == tail || head.next == null){
            return new TreeNode(head.val);
        }

        ListNode slow = head;
        ListNode fast = head;

        ListNode mid = null;

        while ( fast != null && fast.next != null && fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        mid = slow;
        tail = fast;

        TreeNode node = new TreeNode(mid.val);
        node.left = sortedList(head,mid);
        node.right = sortedList(mid.next,tail);

        return node;
    }

    public static void main(String[] args) {
        int[] num = new int[]{-10,-3,0,5,9};

        ListNode head = new ListNode(0);
        ListNode cur = head;

        for(int i = 0;i < num.length;i++){
            ListNode temp = new ListNode(num[i]);
            cur.next = temp;
            cur = cur.next;
        }
        TreeNode treeNode = sortedList2BST(head.next);
    }



}
