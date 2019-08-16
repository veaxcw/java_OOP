package com.chengw.thread.cooperation.producer2consumer;

import java.util.Map;

/**
 * @author veax
 */
public interface StatProcessor {

    void process(String record);

     Map<Long, DelayItem> getResult();
}
