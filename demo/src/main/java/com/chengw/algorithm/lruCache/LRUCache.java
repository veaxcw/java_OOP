package com.chengw.algorithm.lruCache;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * LRU:淘汰最近最少使用的节点
 *
 *
 * 示例:
 * @author chengw
 */
public class LRUCache {


    private HashMap<Integer,DoubleLinkNode> cache;

    private int count;

    private int capacity;

    /**
     * 伪的头结点 和 尾结点
     * **/
    private DoubleLinkNode head,tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(this.capacity);
        head = new DoubleLinkNode();
        tail = new DoubleLinkNode();
        head.next = tail;
        tail.pre = head;
        this.count = 0;
    }

    /**
     * 将每次get地节点放到头结点
     * **/
    public int get(int key) {
        DoubleLinkNode node = cache.get(key);

        if(node == null){
            return  -1;
        }else {
            delNode(node);
            addNode(node);
        }


        return node == null ? -1:node.val;

    }

    public void put(int key, int value) {
        DoubleLinkNode node = cache.get(key);
        if(node == null){
            node = new DoubleLinkNode(key,value);

            cache.put(key,node);
            addNode(node);
            count++;

            if(count > capacity){
                DoubleLinkNode tail = delTail();
                cache.remove(tail.key);
            }

        }else {
            /**
             * 将更新之后的节点放在新的头结点的后面
             * **/
                node.val = value;
                delNode(node);
                addNode(node);

        }

    }

    private void addNode(DoubleLinkNode node){
        /**
         * 新增节点
         * **/
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void delNode(DoubleLinkNode node){

        /**
         * 删除节点
         * **/
        DoubleLinkNode pre = node.pre;
        DoubleLinkNode next = node.next;

        pre.next = next;
        next.pre = pre;

    }

    /**
     * @return 返回被删除的节点
     */
    private DoubleLinkNode delTail(){
        DoubleLinkNode pre = tail.pre;

        delNode(pre);

        return pre;

    }

    class DoubleLinkNode{
        int val;
        int key;
        DoubleLinkNode pre;
        DoubleLinkNode next;

        public DoubleLinkNode() {
        }

        public DoubleLinkNode(int key,int val) {
            this.val = val;
            this.key = key;
        }
    }

}
