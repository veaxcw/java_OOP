package com.chengw.web.socket.tcp;

import com.chengw.web.socket.tcp.server.Server;
import com.chengw.web.socket.tcp.client.ClientThread;

public class server_client {

    public static void main(String[] args) {
        //启动客户端
        new Thread(new ClientThread()).start();

        //启动服务端
        Server server = new Server();
        server.launch();

    }
}
