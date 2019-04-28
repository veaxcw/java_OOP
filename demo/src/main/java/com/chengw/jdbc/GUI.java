package com.chengw.jdbc;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    JTable info;
    MySQLUse read = new MySQLUse();
    public GUI(){
        super("MySQL Test");
        Object colName[] = {"????","??","???","????"};
        try{
            //read.MySQL_read();
            read.PreparedStamentUse();
            Object[][] data = read.getData();
            info = new JTable(data,colName);
            this.add(new JScrollPane(info),BorderLayout.CENTER);
            this.setSize(200,100);
            this.setResizable(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
