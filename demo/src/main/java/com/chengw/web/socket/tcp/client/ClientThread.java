package com.chengw.web.socket.tcp.client;

/**
 * @author Administrator
 */
public class ClientThread implements Runnable {
    @Override
    public void run() {
        new Client();
    }
}
