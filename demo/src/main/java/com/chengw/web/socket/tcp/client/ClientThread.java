package com.chengw.web.socket.tcp.client;

public class ClientThread implements Runnable {
    @Override
    public void run() {
        new com.chengw.web.client.client.Client();
    }
}
