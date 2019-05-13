package com.chengw.algorithm.sort;



public class TestSort {

    public void Test(){
        Thread bumbleThread = new Thread(new BumbleThread());
        Thread insertThread = new Thread(new InsertSortThread());

        bumbleThread.start();
        insertThread.start();

    }
}
