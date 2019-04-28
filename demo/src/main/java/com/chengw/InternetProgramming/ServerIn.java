package com.chengw.InternetProgramming;

import java.io.*;
import java.net.Socket;

public class ServerIn  {
    Socket socket = null;
    public ServerIn(Socket socket){
        this.socket = socket;
    }
    public void run(){
        InputStream ips = null;
        InputStreamReader ipsr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            ips = socket.getInputStream();//��ȡ�������ͻ��˵���Ϣ
            ipsr = new InputStreamReader(ips);//���ֽ���������װ���ַ�������
            br = new BufferedReader(ipsr);//��ipsrд��������
            String info = null;
            while ((info = br.readLine()) != null){
                System.out.println("Client said : " + info);
            }
            socket.shutdownInput();
            os = socket.getOutputStream();//��ȡ�����
            pw = new PrintWriter(os);
            pw.write("welcome");
            pw.flush();//ˢ�»�����
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(ips != null)
                    ips.close();
                if(ipsr != null)
                    ipsr.close();
                if(br != null)
                    br.close();
                if(os != null)
                    os.close();
                if(pw != null)
                    pw.close();
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
