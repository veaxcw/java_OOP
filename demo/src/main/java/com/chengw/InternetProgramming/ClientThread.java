package com.chengw.InternetProgramming;

public class ClientThread implements Runnable {
    @Override
    public void run() {
        new Client();
    }
}
