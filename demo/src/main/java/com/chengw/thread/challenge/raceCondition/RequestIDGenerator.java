package com.chengw.thread.challenge.raceCondition;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Request ID生成器
 * **/
public final class RequestIDGenerator {

    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SEQ_UPPER_LIMIT = 999;
    private short sequence = -1;

    private RequestIDGenerator() {
    }
    /**
     * 生成递增序列号
     * 加上synchronized 后 该方法为线程安全方法， 不会导致竟态
     * **/
    private synchronized short nextSequence(){
        if(sequence >= SEQ_UPPER_LIMIT)
            sequence = 0;
        else {
            sequence ++;

            /**
             *
             * **/
        }
        return sequence;
    }

    public String nextID(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd-HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        /*数字格式化**/
        DecimalFormat decimalFormat = new DecimalFormat("000");

        short sequenceNo = nextSequence();

        return "0049" + timestamp + ":" + decimalFormat.format(sequenceNo);
    }

    public static RequestIDGenerator getInstance(){
        return INSTANCE;
    }

}
