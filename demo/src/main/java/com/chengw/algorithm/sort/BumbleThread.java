package com.chengw.algorithm.sort;

public class BumbleThread implements Runnable {
    @Override
    public void run() {
        new Sort().bumbleSort();
    }
}
