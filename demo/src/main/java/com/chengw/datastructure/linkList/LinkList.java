package com.chengw.datastructure.linkList;

/**
 * @author chengw
 */
public class LinkList<E>{

    private static class Node<E>{
        E data;
        Node pre;
        Node next;

        public Node(Node pre,E data,Node next) {
            this.pre = pre;
            this.data = data;
            this.next = next;
        }
    }


    private int length = 0;
    private Node head;


    private Node tail;

    public void add(E e){
        Node temp = tail;
        Node newNode = new Node(temp,e,null);
        if(temp == null)
            head = tail = newNode;
        else{
            temp.next = newNode;
            tail = newNode;}
        length++;
    }

    public int getLength() {
        return length;
    }
    public Node getHead() {
        return head;
    }


    public void input(){
        for(Node temp = head;temp != null;temp = temp.next) {
            System.out.println(temp.data);
        }
    }

}
