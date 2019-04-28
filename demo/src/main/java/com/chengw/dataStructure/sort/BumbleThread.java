package com.chengw.dataStructure.sort;

public class BumbleThread implements Runnable {
    @Override
    public void run() {
        new Sort().bumbleSort();
    }
}
