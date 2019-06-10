package com.chengw.thread.bigfiledownload;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;
import lombok.Data;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chengw
 */
@Data
public class Storage implements Closeable,AutoCloseable {

    private final RandomAccessFile storeFile;
    private final FileChannel storeChannel;
    protected  final AtomicLong totalWrites = new AtomicLong(0);


    public Storage(long fileSize, String fileShortName) throws IOException {
        String fullFileName = System.getProperty("java.io.tmpdir") + fileShortName;
        String localFileName = creatStoreFile(fileSize, fullFileName);

        storeFile = new RandomAccessFile(localFileName,"rw");
        storeChannel = storeFile.getChannel();

    }

    /**
     * @param offset
     *         写入数据在整个文件中的起始便宜位置
     * @param byteBuffer
     *         byteBuffer 必须在调用该方法前执行byteBuffer.flip()
     * @return
     *          写入数据的长度
     * @throws IOException
     */
    public int store(long offset, ByteBuffer byteBuffer) throws IOException {
        int length;
        storeChannel.write(byteBuffer,offset);
        length = byteBuffer.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    /**
     * 创建临时文件
     * **/
    private String creatStoreFile(final long fileSize,String fullFileName) throws IOException {
        File file = new File(fullFileName);
        Debug.info("create local file:"+fullFileName,fullFileName);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        try {
            randomAccessFile.setLength(fileSize);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Tools.silentClose(randomAccessFile);
        }

        return fullFileName;

    }

    @Override
    public void close() throws IOException {
        if(storeChannel.isOpen()){
            Tools.silentClose(storeChannel,storeFile);
        }
    }
}
