package com.chengw.jvm.memory.directmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存操作demo
 *
 * @author chengwei
 */
public class DirectMemory {

    public static final int CAPACITY = 1;

    public static final int OPERATE_COUNT = 100000000;

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        for (int i = 0;i < OPERATE_COUNT;i++) {
            ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
        }
        Long end = System.currentTimeMillis();
        System.out.println("分配堆内存用时：" + (end - start)+ "ms");

        start = System.currentTimeMillis();
        for (int i = 0;i < OPERATE_COUNT;i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(CAPACITY);
        }
        end = System.currentTimeMillis();
        System.out.println("分配直接内存用时：" + (end - start)+ "ms");


//        ==========================

        byte b = 1;
        ByteBuffer heap = ByteBuffer.allocate(2 * OPERATE_COUNT);
        ByteBuffer direct = ByteBuffer.allocate(2 * OPERATE_COUNT);
        start = System.currentTimeMillis();
        for (int i = 0;i < OPERATE_COUNT;i++) {
            heap.put((byte) 1);
        }
        heap.flip();
        for (int i = 0;i < OPERATE_COUNT;i++) {
            heap.get();
        }
        end = System.currentTimeMillis();
        System.out.println("操作堆内存用时：" + (end - start)+ "ms");

        start = System.currentTimeMillis();
        for (int i = 0;i < OPERATE_COUNT;i++) {
            direct.put((byte) 1);
        }
        direct.flip();
        for (int i = 0;i < OPERATE_COUNT;i++) {
            direct.get();
        }
        end = System.currentTimeMillis();
        System.out.println("操作直接内存用时：" + (end - start)+ "ms");


    }




}
