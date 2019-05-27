package com.chengw.dataStructure.binaryTree;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T data){
        add(data);
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node parent){
        if(parent != null){
            System.out.println(parent.data);
            preOrder(parent.leftChild);
            preOrder(parent.rightChild);
        }

    }

    @Override
    public void inOrder() {
        //todo
    }

    @Override
    public void postOrder() {
        //todo
    }

    @Override
    public Node search(Comparable key) {

        return search(root,key);
    }

    private Node search(Node parent,Comparable key){
        if(key.compareTo(parent.data) == 0){
            return parent;
        }else {
            search(parent.leftChild,key);
            search(parent.rightChild,key);
        }
        return null;
    }

    @Override
    public Node getParent() {
        return null;
    }

    @Override
    public void add(Comparable newEntry) {
        root = add(root,newEntry);
    }

    /**递归实现插入节点**/
    private Node add(Node parent,Comparable e){
        if(parent == null){
            parent = new Node(e);
            size++;
            return parent;
        }else if (!contain(e)){//去除重复
            /**通过递归根据大小插入左节点和右节点**/
           if(e.compareTo(parent.data) < 0)
               parent.leftChild = add(parent.leftChild,e);
           if(e.compareTo(parent.data) >= 0)
               parent.rightChild = add(parent.rightChild,e);
        }
        this.setHeight(height());
        return parent;
    }

    /**迭代实现插入***/
    private void addEntry(Comparable e) {
        //todo
    }

    @Override
    public boolean contain(Comparable data) {
        contain(root,data);
        return false;
    }


    /**递归实现是否包含指定对象**/
    private boolean contain(Node<T> parent,Comparable data){

        if(parent == null)
            return false;
        else if(data.compareTo(parent.data) == 0){
            return true;
        }else {
            if(data.compareTo(parent.data) < 0)
                return contain(parent.leftChild,data);
            if(data.compareTo(parent.data) > 0)
                return contain(parent.rightChild,data);
        }

        return false;
    }



    @Override
    public Node remove() {
        //todo
        return null;
    }


}
