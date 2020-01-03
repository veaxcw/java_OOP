package com.chengw.nio.niocs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用器
 *
 * @author chengw
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel acceptorServer;

    private volatile boolean stop = false;

    /**
     * 初始化服务端
     ***/
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            acceptorServer = ServerSocketChannel.open();

            /**
             * 配置非阻塞模式
             * ***/
            acceptorServer.configureBlocking(false);
            /**
             * 绑定服务端  IP  端口
             * **/
            acceptorServer.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port));


            /**
             * 将channel 注册selector
             *
             * ***/
            acceptorServer.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务已启动：" + port);

        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void stop() {
        stop = true;
    }

    /**
     * 轮询线程
     * 检查已经完成io 的channel 并返回
     ***/
    @Override
    public void run() {
        while (!stop) {
            try {
                /**
                 * 设定延时
                 * ***/
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    handInput(selectionKey);
                    if (selectionKey != null) {
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

//            if(selector != null){
//                try {
//                    selector.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    /**
     * 处理请求 已经完成io的请求
     **/
    private void handInput(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel accept = ssc.accept();
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ);
        }

        if (key.isReadable()) {
            SocketChannel ssc = (SocketChannel) key.channel();
            ByteBuffer bufferedReader = ByteBuffer.allocate(1024);
            int read = ssc.read(bufferedReader);
            if (read > 0) {
                /***
                 * 将Limit 设置为 position;再将位置指针归零，
                 * **/
                bufferedReader.flip();

                byte[] bytes = new byte[bufferedReader.remaining()];

                bufferedReader.get(bytes);

                String body = new String(bytes, "UTF-8");
                System.out.println("服务端已接收：" + body);
                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                doWrite(ssc, currentTime);
            } else if (read < 0) {
                key.cancel();
                ssc.close();
            }

        }


    }

    private void doWrite(SocketChannel ssc, String resp) throws IOException {
        if (ssc != null && resp.trim().length() != 0) {
            byte[] bytes = resp.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            ssc.write(buffer);

        }
    }


}
