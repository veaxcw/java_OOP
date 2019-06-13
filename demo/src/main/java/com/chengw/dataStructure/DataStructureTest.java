package com.chengw.dataStructure;

import com.chengw.dataStructure.binaryTree.BinarySearchTree;
import com.chengw.dataStructure.quene.Queue;
import com.chengw.dataStructure.stack.Stack;


import java.util.Random;

public class DataStructureTest {

    public void binarySearchTreeTest(){
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for(int i = 0;i < 10; i++){
            binarySearchTree.add(r.nextInt(20));
        }
        binarySearchTree.count();
        binarySearchTree.height();
        binarySearchTree.preOrder();
        binarySearchTree.removeAll();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for(int i = 0 ;i < 6;i++){
            queue.add(i);
        }
        while (queue.length() != 0){
            System.out.print(queue.remove() + ",");
        }
    }


}
