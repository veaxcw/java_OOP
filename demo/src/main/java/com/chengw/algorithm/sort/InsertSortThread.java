package com.chengw.algorithm.sort;

public class InsertSortThread implements  Runnable {
    @Override
    public void run() {
        new Sort().insertSort();
    }
}
