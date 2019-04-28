package com.chengw.dataStructure.sort;

public class InsertSortThread implements  Runnable {
    @Override
    public void run() {
        new Sort().insertSort();
    }
}
