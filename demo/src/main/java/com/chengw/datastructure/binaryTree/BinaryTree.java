package com.chengw.datastructure.binaryTree;

public abstract class BinaryTree< T extends Comparable<? super T>> implements Tree{

    protected int size = 0;

    protected void setHeight(int height) {
        this.height = height;
    }

    private int height ;


    protected Node<T> root;

    public BinaryTree() {
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int count() {
        return size;
    }


    @Override
    public int height() {
        height = (int)(Math.log(size)/Math.log(2));
        return height;
    }

    @Override
    public void removeAll() {
        /**这个更加简单粗暴**/
        root = null;
        size = 0;
        height = 0;
    }
    /**递归释放引用**/
    private void remove(Node parent){
        if(parent != null){
            if(parent.rightChild == null && parent.leftChild == null){
                parent = null;

            }else{
                remove(parent.rightChild);
                remove(parent.leftChild);
            }
        }
    }

    public Node<T> getRoot() {
        return root;
    }
}
