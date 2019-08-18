package com.chengw.thread.cooperation.producer2consumer.producer;

import com.chengw.thread.cooperation.producer2consumer.product.RecordSet;
import com.chengw.thread.utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 日志文件读取线程
 *
 * @author veax
 */
public abstract class AbstractLogReader extends Thread {

    protected final BufferedReader logFileReader;

    /**
     * 日志文件是否读取结束
     **/
    protected volatile boolean isEOF = false;

    protected final int batchSize;

    public AbstractLogReader(InputStream in, int inputBufferSize, int batchSize) {
        logFileReader = new BufferedReader(new InputStreamReader(in), inputBufferSize);
        this.batchSize = batchSize;
    }

    public RecordSet getNextToFill() {
        return new RecordSet(batchSize);
    }

    /**
     * 获取下一个记录集
     *
     * @return 获取下一个记录集
     * @throws InterruptedException
     */
    public abstract RecordSet nextBatch() throws InterruptedException;

    /**
     * 发布指定的记录集
     *
     * @param recordBatch
     * @throws InterruptedException
     */
    public abstract void publish(RecordSet recordBatch) throws InterruptedException;

    @Override
    public void run() {
        RecordSet recordSet;
        boolean eof = false;
        try {
            while (true) {
                recordSet = getNextToFill();
                recordSet.reset();
                eof = doFill(recordSet);
                if(eof){
                    if(!recordSet.isEmpty()){
                        publish(new RecordSet(1));
                    }
                    isEOF = eof;
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            Tools.silentClose(logFileReader);
        }
    }

    /**
     * 填充日志集
     *
     * @param recordSet
     * @return
     * @throws IOException
     */
    public boolean doFill(final RecordSet recordSet) throws IOException {
        final int capacity = recordSet.capacity;
        String record;
        for (int i = 0; i < capacity; i++) {
            record = logFileReader.readLine();
            if (null == record) {
                return true;
            }
            recordSet.putRecord(record);
        }
        return false;
    }
}
