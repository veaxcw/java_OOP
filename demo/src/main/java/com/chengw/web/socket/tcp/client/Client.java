package com.chengw.web.socket.tcp.client;

import java.io.*;
import java.net.Socket;

/**
 * @author chengw
 */
public class Client {

    public Client() {
        init();
    }

    private void init(){

        while (true) {
            Socket client = null;
            BufferedReader br = null;

            PrintWriter pw = null;
            try {
                //客户端请求与本机的8888端口建立链接
                client = new Socket("localhost", 8005);
                //获取socket输出流，
                pw = new PrintWriter(client.getOutputStream());
                pw.write("hi countDownLatch!");
                pw.flush();
                client.shutdownOutput();
                //获取从服务端发送过来的数据
                InputStream is = client.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String info;
                if ((info = br.readLine()) != null) {
                    System.out.println("Server said :" + info);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                        br.close();
                        pw.close();
                        client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

