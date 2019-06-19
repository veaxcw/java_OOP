package com.chengw.thread.cooperation.waitandnotify;

/**
 * @author chengw
 */

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;

import java.util.Random;

/**
 * 功能：
 *      线程1：与网络建立链接
 *      线程2：定时检测网络链接状态
 *      线程：发送告警信息
 * @author veax
 */
public class AlarmAgent {

    private static final AlarmAgent INSTANCE = new AlarmAgent();

    private boolean connectedToServer = false;

    private final HeartbeatThread heartbeatThread = new HeartbeatThread();

    public void init(){
        connectToServer();
        /**
         * 设置当前线程为守护线程
         * **/
        heartbeatThread.setDaemon(true);
        heartbeatThread.start();
    }

    public void sendALARM(String message) throws InterruptedException {
        synchronized (this){
            while(!connectedToServer){
                Debug.info("等待链接服务器");
                wait();
            }
            doSendAlarm(message);
        }
    }

    private void doSendAlarm(String message){
        Debug.info(message);
    }




    private AlarmAgent() {
    }

    public static AlarmAgent getInstance(){
        return INSTANCE;
    }

    class HeartbeatThread extends Thread{


        @Override
        public void run() {

            while (true){
                boolean flag = checkConnection();
                Debug.info("心跳监控" + flag);

                if(flag){
                    connectedToServer = true;
                }else {
                    connectedToServer = false;
                    Debug.info("链接中断");
                    connectToServer();

                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private boolean checkConnection(){

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int rand = r.nextInt(1000);
        return rand <= 500?true:false;

    }

    private void connectToServer(){
        new Thread(() -> doConnect()).start();
    }

    private void doConnect(){
        Tools.randomPause(100);
        synchronized (this){
            connectedToServer = true;
            notify();
        }
    }
}
