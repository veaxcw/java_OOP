package com.chengw.InternetProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            int count = 0;//��¼�ͻ��˵Ĵ���
            System.out.println("Server is about to start,waiting for connection from client  ");
            while(true){
                socket = serverSocket.accept();//���ܿͻ��˵�����
                ServerIn st = new ServerIn(socket);
                st.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println("Num_Client :" + count);
                //InetAddress address = socket.getInetAddress();
                //System.out.println("IP_Client:" + address.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
