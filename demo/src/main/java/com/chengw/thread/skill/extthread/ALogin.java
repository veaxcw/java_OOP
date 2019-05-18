package com.chengw.thread.skill.extthread;

import com.chengw.thread.skill.controller.LoginServlet;

/**
 * 模拟登录
 * **/
public class ALogin extends Thread {
    public void run(){
        LoginServlet.doPost("a","aa");
    }
}
