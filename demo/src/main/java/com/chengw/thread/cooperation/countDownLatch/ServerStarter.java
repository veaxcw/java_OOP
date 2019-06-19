package com.chengw.thread.cooperation.countDownLatch;

/**
 * @author veax
 */
public class ServerStarter {

    public static void main(String[] args) {
        ServerManager.startServices();

        boolean allIsOk = ServerManager.checkServiceStatus();

        if(allIsOk){
            System.out.println("所有服务已启动");
        }else{
            System.err.println("服务启动失败");
            System.exit(1);
        }
    }

}
