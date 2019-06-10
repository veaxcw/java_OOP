package com.chengw.thread.bigfiledownload;

import com.chengw.thread.utils.Debug;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author chengw
 */
public class DownloadTest {

    public static void main(String[] args) throws Exception {
        if( 0 == args.length){
            args = new String[]{"http://localhost:8080/download/%E6%B5%8B%E8%AF%95.pdf","2","3"};
        }
        long start = System.currentTimeMillis();
        //download(args);
        download(args,0);
        long end = System.currentTimeMillis();
        System.out.println("下载用时" + (end - start));
    }

    /**
     * 单线程文件下载测试
     * **/
    public static void download(String[] args) throws Exception {
        final int argc = args.length;
        String strUrl = args[0];
        URL url = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        BufferedInputStream bis = null;
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection","Keep-alive");
            connection.setDoInput(true);
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(HttpURLConnection.HTTP_OK != statusCode){
                throw  new Exception("Server exception ,status code:" + statusCode);
            }

            String fileName = strUrl.substring(strUrl.lastIndexOf("/") + 1);
            fileOut = new FileOutputStream("F:/download/"+ fileName);

            byte[] bytes = new byte[1024];

            inputStream = connection.getInputStream();
             bis = new BufferedInputStream(inputStream);
            int len = 0;
            len =  bis.read(bytes);
            while( len >0){
                fileOut.write(bytes);
                len =  bis.read(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                inputStream.close();

            }
            if (fileOut != null) {
                fileOut.close();
            }
            connection.disconnect();
        }




    }

    public static void download(String[] args,int flag) throws Exception {
        final int argc = args.length;

        BigFileDownloader downloader = new BigFileDownloader(args[0]);

        int workerThreadCount = argc >=2?Integer.valueOf(args[1]):2;

        long reportInterval = argc >=3?Integer.valueOf(args[2]):2;

        Debug.info("downloading %s%nConfig:worker threads:%s,reportInterval:%s s.",
                args[0], workerThreadCount,reportInterval);

        downloader.download(workerThreadCount,  reportInterval * 1000);
    }

}
