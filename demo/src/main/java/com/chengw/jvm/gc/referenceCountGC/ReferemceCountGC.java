package com.chengw.jvm.gc.referenceCountGC;

public class ReferemceCountGC {

    public Object instance = null;

    public static final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferemceCountGC objectA = new ReferemceCountGC();
        ReferemceCountGC objectB = new ReferemceCountGC();

        objectA.instance = objectB;
        objectB.instance = objectA;

        objectA = null;
        objectB = null;

        System.gc();
    }

}
