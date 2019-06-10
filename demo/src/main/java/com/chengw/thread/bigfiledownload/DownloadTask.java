package com.chengw.thread.bigfiledownload;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengw
 */
public class DownloadTask implements Runnable {

    /**
     * 下载起始位置
     * **/
    private final long lowerBound;

    /**
     * 下载结束位置
     * **/
    private final long upperBound;

    /**
     * 下载缓冲区
     * **/
    private final DownloadBuffer downloadBuffer;
    /**
     * 是否取消
     * ***/
    private final URL requestURL;

    private final Storage storage;

    private final AtomicBoolean cancelFlag;

    public DownloadTask(long lowerBound, long upperBound, URL requestURL, Storage storage,  AtomicBoolean cancelFlag) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.storage = storage;
        this.downloadBuffer = new DownloadBuffer(lowerBound,upperBound,storage);
        this.requestURL = requestURL;
        this.cancelFlag = cancelFlag;
    }

    private static InputStream issueRequest(URL requestURL,long lowerBound,long upperBound) throws IOException {
        Thread me = Thread.currentThread();
        Debug.info(me + "->[" + lowerBound + "," + upperBound + "]");
        final HttpURLConnection connection;
        InputStream in = null;
        connection = (HttpURLConnection) requestURL.openConnection();
        String strConnectTimeOut = System.getProperty("x.dt.conn.timeout");

        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Connection","Keep-alive");
        connection.setRequestProperty("Charset","UTF-8");
        connection.setRequestProperty("Range","bytes=" + lowerBound + "-" + upperBound);
        connection.setDoInput(true);
        connection.connect();

        int statusCode = connection.getResponseCode();
        if(HttpURLConnection.HTTP_PARTIAL != statusCode){
            connection.disconnect();
            throw new IOException("Server exception,status code:" + statusCode);
        }

        Debug.info(me + "-Content-Range:" + connection.getHeaderField("Content-Range")
                    + ",connection:" + connection.getHeaderField("connection"));

        in = new BufferedInputStream((connection.getInputStream())){
            @Override
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    connection.disconnect();
                }
            }
        };

        return in;

    }

    @Override
    public void run() {
        if(cancelFlag.get()){
            return;
        }
        ReadableByteChannel channel = null;

        try {
            //channel = Channels.newChannel(issueRequest(requestURL,lowerBound,upperBound));
            InputStream inputStream = issueRequest(requestURL, lowerBound, upperBound);
            //ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] buf = new byte[1024];
            int len = 0;
            while (!cancelFlag.get() && (len = inputStream.read(buf)) != -1){
//                /**将从网络中读取的数据写入缓冲区**/
//                downloadBuffer.write(buffer);
//                buffer.clear();
                  storage.store(lowerBound,buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Tools.silentClose(channel,downloadBuffer);
        }

    }
}
