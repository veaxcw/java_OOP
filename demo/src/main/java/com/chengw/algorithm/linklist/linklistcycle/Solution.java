package com.chengw.algorithm.linklist.linklistcycle;


/**
 *给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
**/

import com.chengw.algorithm.linklist.common.ListNode;

import java.util.HashMap;

/**
 * @author chengw
 */
public class Solution {

    public boolean hasCycle(ListNode head) {

        HashMap<Integer,ListNode> map = new HashMap<>();
        int i = 0;

        while (head != null){
            if(!map.containsValue(head.next)){
                map.put(i++,head);
            }else {
                return true;
            }
            head = head.next;
        }

        return false;

    }

    /**
     * 快慢指针
     * **/
    public boolean hasCycle(ListNode head,int a){
        if(head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }

        return true;

    }


}
