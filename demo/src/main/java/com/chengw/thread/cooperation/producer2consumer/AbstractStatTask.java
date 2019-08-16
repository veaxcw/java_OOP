package com.chengw.thread.cooperation.producer2consumer;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.SimpleTimeZone;

/**
 * 统计程序算法步骤的进行抽象
 * @author veax
 */
public abstract class AbstractStatTask implements Runnable {

    private static final String TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private  Calendar calendar;

    private  SimpleDateFormat sdf;
    /**采样周期**/
    private  int sampleInternal;
    /**统计处理业务逻辑**/
    protected  StatProcessor recordProcessor;

    public AbstractStatTask(int sampleInternal,int traceIdDiff,
                            String expectedOperationName,
                            String expectedExternalDeviceList){


    }

    public AbstractStatTask(int sampleInternal,StatProcessor recordProcessor) {
        this.recordProcessor = recordProcessor;
        SimpleTimeZone stz = new SimpleTimeZone(0,"UTC");
        this.sdf = new SimpleDateFormat(TIME_STAMP_FORMAT);
        sdf.setTimeZone(stz);
        this.sampleInternal = sampleInternal;
        this.calendar = Calendar.getInstance(stz);
    }

    public abstract void doCalculate() throws IOException,InterruptedException;

    @Override
    public void run() {
        try {
            doCalculate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<Long, DelayItem> result = recordProcessor.getResult();
        report(result);
    }

    protected void report(Map<Long, DelayItem> summaryResult) {
        int sampleCount;
        final PrintStream ps = System.out;
        ps.printf("%s\t\t%s\t%s\t%s%n",
                "Timestamp", "AvgDelay(ms)", "TPS", "SampleCount");
        for (DelayItem delayStatData : summaryResult.values()) {
            sampleCount = delayStatData.getSampleCount().get();
            ps.printf("%s%8d%8d%8d%n",
                    getUTCTimeStamp(delayStatData
                            .getTimeStamp()), delayStatData.getTotalDelay().get()
                            / sampleCount,
                    sampleCount
                            / sampleInternal, sampleCount);
        }
    }

    private String getUTCTimeStamp(long timeStamp) {
        calendar.setTimeInMillis(timeStamp);
        String tempTs = sdf.format(calendar.getTime());
        return tempTs;
    }
}
