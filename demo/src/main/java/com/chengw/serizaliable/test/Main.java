package com.chengw.serizaliable.test;

import com.chengw.serizaliable.pojo.User;

import java.io.*;

/**
 * @author chengwei
 */
public class Main {

    public static void main(String[] args) throws Exception {
        User cheng = new User("chengw",26);

        String temp = System.getProperty("java.io.tmpdir");

        File file = new File(temp + File.separator + "user.dat");

        System.out.println(temp);

        if (file.exists() && !file.delete()) {
            throw new Exception();
        }
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException();
        }


        try (FileOutputStream fos = new FileOutputStream(file);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream object = new ObjectOutputStream(outputStream);) {
            object.writeObject(cheng);
            object.flush();
            fos.write(outputStream.toByteArray());
        }
    }

}
