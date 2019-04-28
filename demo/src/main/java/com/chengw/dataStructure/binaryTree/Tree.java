package com.chengw.dataStructure.binaryTree;

public interface Tree<T extends Comparable<? super T>> {

    /**判断是否是空**/
    boolean  isEmpty();

    /**节点数**/
    int count();

    /**高度**/
    int height();

    /**先序遍历**/
    void preOrder();

    /**中序遍历**/
    void inOrder();

    /**后序遍历**/
    void postOrder();

    /**查找并且返回指定节点**/
    Node<T> search(T key);

    /**返回node的parent节点**/
    Node<T> getParent();

    /****/
    void add(T newEntry);

    /**删除节点并返回删除的节点**/
    Node<T> remove();

    /**删除树*/
    void removeAll();

    boolean contain(T data);


}
