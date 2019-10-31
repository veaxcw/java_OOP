package com.chengw.web.pressure;

/**
 * 压力测试
 * @author chengwei
 */
public class Pressure  {

    public static final String HOST = "http://localhost:8666/tiafs/v1/users";

    public static final String PORT = "8666";

    public static final String ROUTER = "";

    public static void main(String[] args) {
        RequestThread requestThread = new RequestThread(HOST + "/1",Constant.GET,1);
        Thread thread = new Thread(requestThread);
        thread.start();
    }



}
