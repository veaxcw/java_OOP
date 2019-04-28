package com.chengw.InternetProgramming;

public class ServerThread implements  Runnable {
    @Override
    public void run() {
        new Server();

    }
}
