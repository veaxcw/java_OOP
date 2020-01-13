package com.chengw.nio.niocs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

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
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * 链接服务端
     * @throws IOException
     */
    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }

    }

    /**
     * 写入数据
     */
    private void doWrite(SocketChannel sc){

        byte[] bytes = "QUERY TIME ORDER".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("bytes[]" + bytes);
        buffer.put(bytes);
        buffer.flip();
        try {
            sc.write(buffer);
            if(!buffer.hasRemaining()){
                System.out.println(" send order 2 server succeed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void run() {
        try {
            doConnect();
            System.out.print(Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        /***
         * 检查可以是否是有效
         * **/
        if (key.isValid()) {
            SocketChannel channel = (SocketChannel)key.channel();
            if(key.isConnectable()){
                /**
                 * 返回为true 则channel 是connected
                 * **/
                if(channel.finishConnect()){
                    channel.register(selector,SelectionKey.OP_READ);
                    doWrite(channel);

                }else {
                    System.exit(1);
                }
            }

            if(key.isReadable()){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int read = channel.read(buffer);
                if(read > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("Now is :" + body);
                    this.stop = true;
                }else if(read < 0){
                    key.cancel();
                    channel.close();
                }
            }
        }

    }
}
