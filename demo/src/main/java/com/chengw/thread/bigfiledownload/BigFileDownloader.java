package com.chengw.thread.bigfiledownload;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengw
 */
public class BigFileDownloader {

    protected final URL requestURL;

    protected final long fileSize;

    protected final Storage storage;

    protected final AtomicBoolean taskCancel = new AtomicBoolean(false);



    public BigFileDownloader(String strUrl) throws Exception {


        this.requestURL = new URL(strUrl);
        fileSize = receiveFileSize(requestURL);
        Debug.info("file total size:" + fileSize/(1024*1024) +"M");
        String url = URLDecoder.decode(strUrl, "UTF-8");
        String fileName = url.substring(url.lastIndexOf("/")+1);
        storage = new Storage(fileSize,fileName);
    }

    /**
     * @param taskCount
     *           任务个数
     * @param reportInterval
     *            下载进度报告周期
     */
    public void download(int taskCount,long reportInterval) throws InterruptedException {

        long chunkSizePerThread = fileSize/taskCount;

        long lowerBound = 0;
        long upperBound = 0;
        DownloadTask downloadTask;
        for(int i = taskCount - 1;i >= 0;i--){
            lowerBound = i*chunkSizePerThread;
            if(i == taskCount - 1){
                upperBound = fileSize;
            }else {
                upperBound = lowerBound + chunkSizePerThread - 1;
            }

            downloadTask = new DownloadTask(lowerBound,upperBound,requestURL,storage, taskCancel);
            dispatchWork(downloadTask,i);
            //reportProgress(reportInterval);
            doCleanUp();
        }

    }

    protected  void dispatchWork(final DownloadTask downloadTask,int workerIndex){
        Thread workerThread = new Thread(()->{
            try {
                downloadTask.run();
            } catch (Exception e) {
                e.printStackTrace();
                cancelDownLoad();
            }
        });
        workerThread.setName("downloader-" + workerIndex);
        workerThread.start();

    }

    protected void doCleanUp(){
        Tools.silentClose(storage);
    }

    private void cancelDownLoad(){
        if(taskCancel.compareAndSet(false,true)){
            doCleanUp();
        }
    }

    /**
     * 根据URL 获取文件大小
     * @param requestURL
     * @return
     * @throws Exception
     */
    private static long receiveFileSize(URL requestURL) throws Exception {

        long size = -1;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setRequestProperty("Connection","Keep-alive");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(HttpURLConnection.HTTP_OK != statusCode){
                throw  new Exception("Server exception ,status code:" + statusCode);
            }
            /**以 八位字节数组 （8位的字节）表示的请求体的长度*/
            String headerField = connection.getHeaderField("Content-Length");
            size = Long.valueOf(headerField);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != connection){
                connection.disconnect();
            }
        }

        return size;

    }

    /**
     *  报告下载进度
     *  **/
    private void reportProgress(long reportInterval) throws InterruptedException {
        float lastCompletion;
        int completion = 0;
        while (!taskCancel.get()) {
            lastCompletion = completion;
            completion = (int) (storage.getTotalWrites().get() * 100 / fileSize);
            if (completion == 100) {
                break;
            } else if (completion - lastCompletion >= 1) {
                Debug.info("Completion:" + completion + "%");
                if (completion >= 90) {
                    reportInterval = 1000;
                }
            }
            Thread.sleep(reportInterval);
        }
        Debug.info("Completion:" + completion + "%");
    }
}

