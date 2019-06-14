package com.chengw.algorithm.linklist.myLinkList;
class MyLinkedList {

    private  class Node<Integer>{
        Integer data;
        Node pre;
        Node next;

        public Node(Node pre,Integer data,Node next) {
            this.pre = pre;
            this.data = data;
            this.next = next;
        }
    }

    private int length = 0;
    private Node head;
    private Node tail;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(head == null || index < 1){
            return -1;
        }
        Node node = head;
        int i = 1;
        while(node != null){
            if(index == i++){
                return (int) node.data;
            }
            node = node.next;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if(head == null){
            head = tail = new Node(null,val,null);
        }else{
            Node node = new Node(null,val,head);
            head.pre = node;
            head = node;
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if(head == null){
            head = tail = new Node(null,val,null);
        }else{
            Node node = new Node(tail,val,null);
            tail.next = node;
            tail = node;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        Node node = getNode(index);
        Node temp = new Node(node.pre,val,node);
        if(node.pre == null){
            head = temp;
        }else{
            node.pre.next = temp;
            node.pre = temp;
        }

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        Node del = getNode(index);
        if(del.pre == null){
            head = del.next;
            del = null;
        } else if(del.next == null){
            tail = del.pre;
            del = null;
        }else{
            del.pre.next = del.next;
            del.next.pre = del.pre;
            del = null;
        }
    }

    private Node getNode(int index) {

        Node node = head;
        int i = 1;
        while(node != null){
            if(index == i++){
                return node;
            }
            node = node.next;
        }
        return null;
    }


    public static void main(String[] args) {


    MyLinkedList obj = new MyLinkedList();
        int a = obj.get(0);
        obj.addAtIndex(1,2);
        obj.get(0);
        obj.get(1);
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1,2);
        int i = obj.get(1);
        obj.deleteAtIndex(1);
        int j = obj.get(1);

    }

}
