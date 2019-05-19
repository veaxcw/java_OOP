package com.chengw.thread.challenge.atomic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 必须使用32位java虚拟机才能看到效果
 * 或者使用 -client
 * long double 的读写操作不是原子操作
 * */
public class NonAtomicAssignmentDemo implements Runnable {

    static long value = 0;
    private final long valueToSet;

    public NonAtomicAssignmentDemo(long valueSet) {
        this.valueToSet = valueSet;
    }

    public static void main(String[] args) {
        Thread updateThread1 = new Thread(new NonAtomicAssignmentDemo(0L));
        Thread updateThread2 = new Thread(new NonAtomicAssignmentDemo(1L));

        PrintWriter pw = new PrintWriter(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                //啥也不干
            }
        });
        updateThread1.start();
        updateThread2.start();

        long snapshot;

        while(0 == (snapshot = value) || -1 == snapshot){
            pw.print(snapshot);
        }

        System.err.printf("Unexpected data:%d(0x%016x)",snapshot,snapshot);
        pw.close();
        System.exit(0);
    }

    @Override
    public void run() {
        for (;;){
            value = valueToSet;
        }
    }
}
