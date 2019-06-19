package com.chengw.thread.cooperation.waitandnotify;

/**
 * @author veax
 */
public class AlarmTest {

    private final static AlarmAgent alarmAgent;

    static {
        alarmAgent = AlarmAgent.getInstance();
        alarmAgent.init();
    }

    public static void main(String[] args) {


        try {
            for (int i = 0;i < 100;i++) {
                alarmAgent.sendALARM("这是第"+ i +"个警告");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
