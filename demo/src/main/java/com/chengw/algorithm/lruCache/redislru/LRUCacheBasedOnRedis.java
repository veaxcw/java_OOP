package com.chengw.algorithm.lruCache.redislru;

import redis.clients.jedis.Jedis;

/**
 * 基于Redis 实现 LRU
 * @author chengw
 */
public class LRUCacheBasedOnRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        int i = 0;
        try {
            long start = System.currentTimeMillis();

            while (true){
                long end = System.currentTimeMillis();
                if((end - start) > 1000){
                    break;
                }
                i++;
                jedis.set("test"+i,i+"");

            }
        } finally {
            jedis.close();
        }
        System.out.println("redis 一秒内操作："+ i + "次");
     }

}
