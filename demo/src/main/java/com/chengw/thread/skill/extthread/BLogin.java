package com.chengw.thread.skill.extthread;

import com.chengw.thread.skill.controller.LoginServlet;

public class BLogin extends Thread {

    @Override
    public void run() {
        LoginServlet.doPost("b","bb");
    }
}
