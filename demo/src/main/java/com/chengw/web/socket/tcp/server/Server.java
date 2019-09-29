package com.chengw.web.socket.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chengw
 */
public class Server  {


    public Server() {

    }

    public void launch(){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket client;
            int count = 0;
            System.out.println("Server is about to start,waiting for connection from client  ");
            while(count <= 1){
                client = serverSocket.accept();
                new Thread(new ServerThread(client)).start();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println("Num_Client :" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
