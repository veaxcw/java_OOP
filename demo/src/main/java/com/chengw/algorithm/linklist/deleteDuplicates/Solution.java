package com.chengw.algorithm.linklist.deleteDuplicates;

import com.chengw.algorithm.linklist.common.ListNode;

import java.util.*;
/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * ***/


/**
 * @author chengw
 */
public class Solution {


    /**todo**/
    public static ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new TreeSet<>();

        ListNode temp = head;

        while(temp != null){

            set.add(temp.val);

            temp = temp.next;
        }


        ListNode rsp = new ListNode(0);
        ListNode resut = rsp;

        for(Integer i:set){
            ListNode node = new ListNode(i);
            rsp.next = node;
            rsp = node;

        }

        return rsp;

    }

    public ListNode delDuplictes(ListNode head){
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        deleteDuplicates(head);
    }
}
