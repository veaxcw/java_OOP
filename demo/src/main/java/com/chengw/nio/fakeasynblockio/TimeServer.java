package com.chengw.nio.fakeasynblockio;

import com.chengw.nio.bio.TimeServerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 伪异步阻塞模型
 * @author chengw
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length != 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("服务端已启动,端口号：" + port);
            Socket socket = null;

            /**
             * 创建io任务线程池
             * ***/
            TimerServerHandlerExcutePool singleExecutor = new TimerServerHandlerExcutePool(10, 10000);

            while (true) {
                socket = server.accept();
                if(socket != null){
                    singleExecutor.execute(new TimeServerHandler(socket));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server != null){
                try {
                    server.close();
                    System.out.println("服务端已关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
