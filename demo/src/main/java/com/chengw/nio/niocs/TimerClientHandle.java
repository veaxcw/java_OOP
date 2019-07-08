package com.chengw.nio.niocs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 基于NIOde客户端
 * @author chengw
 */
public class TimerClientHandle implements Runnable {

    private String host;

    private SocketChannel socketChannel;

    private int port;

    private volatile boolean stop = false;

    private Selector selector;


    /**
     * 初始化地址
     * @param host
     * @param port
     */
    public TimerClientHandle(String host, int port) {
        this.host = host == null? "localhost":host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel = SocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector, SelectionKey.OP_READ);

        }

    }

    private void doWrite(){

        byte[] bytes = " QUERY TIME ORDER".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(bytes);
        buffer.flip();
        try {
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void run() {

    }
}
