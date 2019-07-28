package com.chengw.jvm.gc.eden;

/**
 * @author veax
 */
public class MinorGC {

    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] a,b,c,d;
        a = new byte[_1MB / 4];
        b = new byte[2 * _1MB];
        c = new byte[2 * _1MB];
        c = new byte[4 * _1MB];
    }

}
