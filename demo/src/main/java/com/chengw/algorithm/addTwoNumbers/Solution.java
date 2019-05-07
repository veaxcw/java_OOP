package com.chengw.algorithm.addTwoNumbers;


/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * */
public class Solution {

    /**定义一个链表的**/
    static class ListNode{

       int val;
       ListNode next;
       public ListNode(int val) {
           this.val = val;
       }

    }

   public static ListNode addTwoNumbers(ListNode l1,ListNode l2){


       ListNode listNode = new ListNode(0);

       int val;

        //代码简洁之道
       val = l1 == null?(0 + (l2 == null ?0:l2.val)):(l1.val + (l2 == null ?0:l2.val));

       listNode.val = val%10;

       /**
       *逢10进1
       * */
       if(val/10 > 0) {
           l1.next = l1.next == null?new ListNode(0):l1.next;
           l2.next = l2.next == null?new ListNode(0):l2.next;
           l1.next.val++;

       }else {
           /**
            * 递归结束标志
            * 低位小于10且高位为空
            * **/
           if(l1.next == null && l2.next == null){
               return listNode;
           }
           l1.next = l1.next == null?new ListNode(0):l1.next;
           l2.next = l2.next == null?new ListNode(0):l2.next;

       }
       listNode.next = addTwoNumbers(l1.next,l2.next);
       return listNode;

   }

    public static void main(String[] args) {

       ListNode l1 = new ListNode(5);
       //l1.next = new ListNode(8);
       //l1.next.next = new ListNode(3);

       ListNode l2 = new ListNode(5);
        //l2.next = new ListNode(9);
        //l2.next.next = new ListNode(4);

       ListNode l3 =  addTwoNumbers(l1,l2);

       while(l3 != null){
           System.out.print(l3.val);
           if(l3.next != null)
                System.out.print("->");
           l3 = l3.next;
       }




    }


}
