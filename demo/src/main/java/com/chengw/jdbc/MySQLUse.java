package com.chengw.jdbc;




import java.sql.*;

import java.util.Scanner;

public class MySQLUse{
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test?" +
            "useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
            //"useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//�α겻�α��ķ���
    private String username = "root";
    private String password = "xveacw8023";
    private String sql = "select * from info";



    public Object[][] getData() {
        return data;
    }

    private Object data[][];

    public Connection MySQLConn(){
        Connection conn = null;
        try{
            try{
                Class.forName(driver);
            }catch(Exception e){
                System.out.println("�޷���������");
            }
            conn = DriverManager.getConnection(url,username,password);
            if(!conn.isClosed()){
                System.out.println("Success Connected to MySQL");
                return conn;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;

    }

    public void MySQL_read(){//����Statementִ�в�ѯ����
        try{
            Connection conn = MySQLConn();
            Statement stmt = conn.createStatement();//����ִ�в���������SQL���
            // PreparedStatement ��������ִ�д��򲻴� IN ������Ԥ���� SQL ��䣻
            //CallableStatement ��������ִ�ж����ݿ��Ѵ洢���̵ĵ��á�

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();//��λ�����һ��
            int row = rs.getRow();
            rs.beforeFirst();
            data = new Object[row][10];
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                i++;
            }
            rs.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void MySQL_Write(int col,int row){//��������д������
        Scanner sc = new Scanner(System.in);
        String Creat_Table = "CREATE TABLE if not exists info (" +
                " id int AUTO_INCREMENT" + " PRIMARY KEY," +
                "���� varchar(10)," +
                "רҵ varchar(10)," +
                "ѧ�� varchar(10)," +
                "���� int" +
                ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=gbk;";//�������

        try{
            Connection conn = MySQLConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(Creat_Table);
            String[] ColName = getColName();
            for(int j = 0; j < row -1; j++) {
                StringBuffer input_table = new StringBuffer("insert into info (����,רҵ,ѧ��,����) values");
                input_table.append("(");
                for(int i = 0; i < col - 1;i++){
                    System.out.println("please input " + ColName[i+1]);
                    input_table.append("\"" + sc.next() + "\""+ ",");
                }
                System.out.println("please input data");
                input_table.append(sc.next());
                input_table.append(");");
                String Input_Table = new String(input_table);
                System.out.println(Input_Table);
                stmt.executeUpdate(Input_Table);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public String[] getColName(){//��ȡÿ�е�����
        int count;
        String[] ColName = null;
        try {
            Connection conn = MySQLConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();//MetaData:data about data(�������Ϊ�������߽��ͺ��������)
            count = rsmd.getColumnCount();//�����е���Ŀ
            ColName = new String[count];
            for(int i = 0;i < count; i++)
                ColName[i] = rsmd.getColumnName(i + 1);//���ص�i+1�е�����
            rs.close();
            conn.close();
            return ColName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ColName;
    }

    public void PreparedStamentUse(){
        try {
            Connection conn = MySQLConn();
            PreparedStatement pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstmt.executeQuery(sql);
            rs.last();//��λ�����һ��
            int row = rs.getRow();
            rs.beforeFirst();
            data = new Object[row][10];
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                i++;
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

