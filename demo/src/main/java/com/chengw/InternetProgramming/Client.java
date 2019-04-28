package com.chengw.InternetProgramming;

import java.io.*;
import java.net.Socket;

public class Client {
    public Client() {
        while (true) {
            BufferedReader br = null;
            OutputStream os = null;
            PrintWriter pw = null;
            Socket socket = null;
            try {
                socket = new Socket("localhost", 8888);
                os = socket.getOutputStream();
                pw = new PrintWriter(os);
                pw.write("hi server!");
                pw.flush();
                socket.shutdownOutput();
                InputStream is = socket.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String info = null;
                if ((info = br.readLine()) != null)
                    System.out.println("Servet said :" + info);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (os != null)
                        os.close();
                    if (pw != null)
                        pw.close();
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
