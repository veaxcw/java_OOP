package com.chengw.nio.niocs;

import java.io.IOException;


/**
 * 基于 NIO 的服务端demo
 * @author chengw
 */
public class Server {

    private static final int  port = 9999;



    public static void main(String[] args) {

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer,"服务端 - 1").start();

    }

}
