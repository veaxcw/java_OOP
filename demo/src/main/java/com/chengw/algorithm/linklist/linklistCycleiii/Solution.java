package com.chengw.algorithm.linklist.linklistCycleiii;

import com.chengw.algorithm.linklist.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。

 * @author chengw
 */
public class Solution {

    private List<ListNode> list = new ArrayList<>();

    public ListNode detectCycle(ListNode head) {

        while(head != null){

            if(list.contains(head)){
                return head;
            }
            list.add(head);
            head = head.next;
        }
        return null;
    }

}
