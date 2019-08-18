package com.chengw.thread.cooperation.producer2consumer;

import com.chengw.thread.cooperation.producer2consumer.product.DelayItem;

import java.util.Map;

/**
 * @author veax
 */
public interface StatProcessor {

    void process(String record);

     Map<Long, DelayItem> getResult();
}
