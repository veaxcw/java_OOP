package com.chengw.thread.skill.extthread;

public class Run {

    public static void main(String[] args) {
        ALogin a = new ALogin();

        BLogin b = new BLogin();
        for (int i = 0;i < 10;i++) {
            a.start();
            b.start();
        }
    }


}
