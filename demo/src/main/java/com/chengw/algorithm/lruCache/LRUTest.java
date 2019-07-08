package com.chengw.algorithm.lruCache;

/**
 * @author chengw
 */
public class LRUTest {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        int i = cache.get(1);
        cache.put(3,3);
        int i1 = cache.get(2);
    }
}

