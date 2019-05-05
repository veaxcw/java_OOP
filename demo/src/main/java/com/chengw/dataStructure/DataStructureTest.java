package com.chengw.dataStructure;

import com.chengw.dataStructure.binaryTree.BinarySearchTree;
import org.junit.Test;


import java.util.Random;

public class DataStructureTest {

    @Test
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


}
