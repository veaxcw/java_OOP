package com.chengw.thread.cooperation.producer2consumer;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * 记录处理器
 *
 * @author chengw
 */
public class RecordProcessor implements StatProcessor {

    private Map<Long,DelayItem> summaryResult;

    private static final FastTimeStampParser FAST_TIME_STAMP_PARSER = new FastTimeStampParser();

    private static final DecimalFormat df = new DecimalFormat("0000");

    private static final int INDEX_TIMESTAMP = 0;
    private static final int INDEX_TRACE_ID = 7;
    private static final int INDEX_MESSAGE_TYPE = 2;
    private static final int INDEX_OPERATION_NAME = 4;
    private static final int SRC_DEVICE = 5;
    private static final int DEST_DEVICE = 6;

    private static final int FIELDS_COUNT = 11;

//    private static final Map<String,DelayData> immediateResult;

    private final int traceIdDiff;
    private final String expectedOperationName;
    private String selfDevice = "ESB";

    private long currRecordDate;

    private final int sampleInterval;
    private final String expectedExternalDeviceList;

    public RecordProcessor(int sampleInterval,int traceIdDiff,
                           String expectedOperationName,String expectedExternalDeviceList){
        summaryResult = new TreeMap<>();
        this.sampleInterval = sampleInterval;
        this.traceIdDiff = traceIdDiff;
        this.expectedOperationName = expectedOperationName;
        this.expectedExternalDeviceList = expectedExternalDeviceList;
    }





    @Override
    public void process(String record) {

    }



    @Override
    public Map<Long, DelayItem> getResult() {
        return null;
    }

    /**
     * 延时响应数据
     * ***/
    class DelayData{
        private String traceId;
        private String operationName;
        private String reqTime;
        private String repTime;

        public DelayData() {
        }


        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getOperationName() {
            return operationName;
        }

        public void setOperationName(String operationName) {
            this.operationName = operationName;
        }

        public String getReqTime() {
            return reqTime;
        }

        public void setReqTime(String reqTime) {
            this.reqTime = reqTime;
        }

        public String getRepTime() {
            return repTime;
        }

        public void setRepTime(String repTime) {
            this.repTime = repTime;
        }
    }
}
