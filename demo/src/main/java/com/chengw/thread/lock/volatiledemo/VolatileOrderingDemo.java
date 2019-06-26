package com.chengw.thread.lock.volatiledemo;

import com.chengw.thread.utils.stf.*;


/**
 * 我也没看懂这个方法是干嘛使的
 *
 * @author chengw***/
@ConcurrencyTest(iterations = 200000)
public class VolatileOrderingDemo {

    private int dataA = 0;
    private long dataB = 0L;
    private String dataC = null;
    private volatile boolean ready = false;

    @Actor
    public void writer(){
        dataA = 1;
        dataB = 10000L;
        dataC = "chengw";
        ready = true;
    }

    @Observer({
            @Expect(desc = "Normal",expected = 1),
            @Expect(desc = "Impossible",expected = 2),
            @Expect(desc = "ready not true",expected = 3)
    })
    public int reader(){
        int result = 0;
        boolean allIsOk;
        if(ready){
            allIsOk = (1 == dataA)&&(10000L == dataB)&&("chengw".equals(dataC));
            result = allIsOk?1:2;
        }else {
         result = 3;
        }

        return result;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        TestRunner.runTest(VolatileOrderingDemo.class);
    }

}
