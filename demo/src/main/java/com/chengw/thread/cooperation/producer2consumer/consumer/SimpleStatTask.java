package com.chengw.thread.cooperation.producer2consumer.consumer;

import com.chengw.thread.cooperation.producer2consumer.StatProcessor;
import com.chengw.thread.cooperation.producer2consumer.consumer.AbstractStatTask;
import com.chengw.thread.utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author veax
 */
public class SimpleStatTask extends AbstractStatTask {

    private final InputStream in;

    public SimpleStatTask(InputStream in,int sampleInternal, StatProcessor recordProcessor) {
        super(sampleInternal, recordProcessor);
        this.in = in;
    }

    @Override
    public void doCalculate() throws IOException, InterruptedException {
        String strBufferSize = System.getProperty("x.input.buffer");
        int inputBufferSize = null != strBufferSize ? Integer
                .valueOf(strBufferSize) : 8192 * 4;
        final BufferedReader logFileReader = new BufferedReader(
                new InputStreamReader(in), inputBufferSize);
        String record;
        try {
            while ((record = logFileReader.readLine()) != null) {
                // 实例变量recordProcessor是在AbstractStatTask中定义的
                recordProcessor.process(record);
            }
        } finally {
            Tools.silentClose(logFileReader);
        }
    }
}
