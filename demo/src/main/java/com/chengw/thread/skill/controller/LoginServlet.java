package com.chengw.thread.skill.controller;

import lombok.ToString;

/**
 * 模拟servlet login
 * **/
@ToString
public class LoginServlet {

    private static String usernameRef;

    private static  String passwordRef;

    public static void doPost(String username,String password){
        try {
            usernameRef = username;

            if(username.equals("a"))
                Thread.sleep(5000);
            passwordRef = password;
            System.out.println("username:" + username + "\n password = "
            + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
