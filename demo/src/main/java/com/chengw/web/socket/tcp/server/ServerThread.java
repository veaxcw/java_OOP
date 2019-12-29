package com.chengw.web.socket.tcp.server;

import java.io.*;
import java.net.Socket;

/**
 * 接收客户端发送过来的信息
 *
 * @author Administrator
 */
public class ServerThread implements Runnable {

    private Socket client;

    public ServerThread(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        OutputStream os = null;
        PrintWriter pw = null;
        try (InputStream ips = client.getInputStream();
             InputStreamReader ipsr = new InputStreamReader(ips);
             BufferedReader br = new BufferedReader(ipsr);) {

            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("Client said : " + info);
            }
            client.shutdownInput();
            os = client.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("welcome");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
