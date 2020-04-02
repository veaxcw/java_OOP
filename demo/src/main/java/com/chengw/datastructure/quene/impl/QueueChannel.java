package com.chengw.datastructure.quene.impl;

import com.chengw.datastructure.quene.IChannel;

/**
 * @author chengwei
 */
public class QueueChannel<T> implements IChannel<T> {


    private Node head;

    private Node tail;

    private Integer size = 0;

    public QueueChannel() {
    }

    @Override
    public T pop() {
        T element = (T) head.e;
        head = head.next;
        size--;
        return element;
    }

    @Override
    public void push(T o) {

        if (head == null) {
            head = new Node(null, null, o);
            tail = head;
        }else {
            Node node = new Node(tail, null, o);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public Integer getSize() {

        return size;
    }

    private static class Node<T> {
        T e;
        Node<T> pre;

        Node<T> next;

        Node(Node<T> pre,Node<T> next,T e) {
            this.e = e;
            this.pre = pre;
            this.next = next;
        }
    }
}
