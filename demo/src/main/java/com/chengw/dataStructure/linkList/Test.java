package com.chengw.dataStructure.linkList;

import java.util.Random;
import java.util.Scanner;

public class Test {
    LinkList_DIY<Integer> example = new LinkList_DIY<Integer>();

    Random rand = new Random();

    private void init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please input the size of example:");
        int size = sc.nextInt();
        for(int i = 0; i < size; i++)
            example.add(rand.nextInt(2*size));
       example.input();

    }

    public static void main(String[] args) {
        Test test = new Test();
        test.init();
        System.out.println(test.example.getLength());

    }
}
