package com.chengw.thread.cooperation.producer2consumer.product;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chengw
 */
@Data
public class DelayItem {
    private long timeStamp;
    private AtomicInteger sampleCount = new AtomicInteger(0);
    private AtomicLong totalDelay = new AtomicLong(0);

}
