package com.chengw.thread.bigfiledownload;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengw
 */
public class DownloadTask implements Runnable {


    private final long lowerBound;

    private final long upperBound;


    private final DownloadBuffer downloadBuffer;

    private final URL requestURL;

    private final AtomicBoolean cancelFla;

    public DownloadTask(long lowerBound, long upperBound,  URL requestURL,Storage storage, AtomicBoolean cancelFla) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.downloadBuffer = new DownloadBuffer(lowerBound,upperBound,storage);
        this.requestURL = requestURL;
        this.cancelFla = cancelFla;
    }

    private static InputStream issueRequest(URL requestURL,long lowerBound,long upperBound){
        Thread me = Thread.currentThread();
        Debug.info(me + "->[" + lowerBound + "," + upperBound + "]");
        final HttpURLConnection connection;
        InputStream in = null;
        try {
            connection = (HttpURLConnection) requestURL.openConnection();
            String strConnectTimeOut = System.getProperty("x.dt.conn.timeout");

            int connTimeOut = null == strConnectTimeOut?60000: Integer.valueOf(strConnectTimeOut);
            connection.setConnectTimeout(connTimeOut);


            String strReadTimeOut = System.getProperty("x.dt.read.timeout");

            int readTimeOut = null == strReadTimeOut?60000: Integer.valueOf(strReadTimeOut);
            connection.setReadTimeout(readTimeOut);

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection","Keep-alive");
            connection.setRequestProperty("Range","bytes" + lowerBound + "-" + upperBound);
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;

    }

    @Override
    public void run() {
        if(cancelFla.get()){
            return;
        }
        ReadableByteChannel channel = null;

        try {
            channel = Channels.newChannel(issueRequest(requestURL,lowerBound,upperBound));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (!cancelFla.get() && channel.read(buffer) > 0){
                downloadBuffer.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Tools.silentClose(channel,downloadBuffer);
        }

    }
}
