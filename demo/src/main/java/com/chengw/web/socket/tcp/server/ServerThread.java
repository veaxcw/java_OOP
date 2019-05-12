package com.chengw.web.socket.tcp.server;

import java.io.*;
import java.net.Socket;
/**
 * 接收客户端发送过来的信息
 * */
public class ServerThread implements  Runnable {

    private Socket client;

    public ServerThread(Socket socket){
        this.client = socket;
    }

    public void run(){
        InputStream ips = null;
        InputStreamReader ipsr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            ips = client.getInputStream();
            ipsr = new InputStreamReader(ips);
            br = new BufferedReader(ipsr);
            String info = null;
            while ((info = br.readLine()) != null){
                System.out.println("Client said : " + info);
            }
            client.shutdownInput();
            os = client.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("welcome");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(ips != null)
                    ips.close();
                if(ipsr != null)
                    ipsr.close();
                if(br != null)
                    br.close();
                if(os != null)
                    os.close();
                if(pw != null)
                    pw.close();
                if(client != null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
