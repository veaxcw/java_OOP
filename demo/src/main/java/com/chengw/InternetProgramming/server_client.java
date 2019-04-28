package com.chengw.InternetProgramming;

public class server_client {

    public static void main(String[] args) {
        ClientThread client = new ClientThread();
        ServerThread server = new ServerThread();
        Thread th_server = new Thread(server);
        Thread th_client = new Thread(client);
        th_server.start();
        th_client.start();

    }
}
