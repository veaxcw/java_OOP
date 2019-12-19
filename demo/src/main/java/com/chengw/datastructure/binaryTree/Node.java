package com.chengw.datastructure.binaryTree;


/**二叉树节点**/
/**规定T必须实现Comparable接口**/
public class Node<T extends Comparable<? super T>>{
    protected int index;
    protected Node<T> leftChild,rightChild;
    protected T data;

    public Node(){}

    public Node(  T data) {
        this.leftChild = null;
        this.rightChild = null;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }



    public Node<T> getLeftChild() {
        return leftChild;
    }



    public Node<T> getRightChild() {
        return rightChild;
    }


    public T getData() {
        return data;
    }

}
