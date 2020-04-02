package com.chengw.jdbc.utils;

import com.chengw.jdbc.po.SysUserBean;
import com.chengw.jdbc.po.UserBean;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chengw
 */
public class JdbcConnectionUtils {

    /**
     * 禁止实例化
     ***/
    private JdbcConnectionUtils() {
    }

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://59.110.230.138:3306/his?" +
            "useUnicode=true&" +
            "characterEncoding=UTF-8&" +
            "allowMultiQueries=true&" +
            "serverTimezone=GMT%2B8";
    private final static String USERNAME = "veax";
    private final static String PASSWORD = "veadbpass";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!conn.isClosed()) {
                System.out.println("Success Connected to MySQL");
                return conn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to connect database");
        }

        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }


    /**
     * 批量插入1万条用户测试数据
     ****/
    public static void insetBatch(Connection conn, List<SysUserBean> userBeans) throws SQLException {

        assert conn != null;

        String sql = "INSERT INTO sys_user( `id`,`name`, `email`, `sex`, `phone`,`password`,`user_name`,`created_date`, `updated_date`) " +
                "VALUES  (?,?,?,?,?,?,?,?,?)";

        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (SysUserBean u : userBeans) {
            preparedStatement.setLong(1, u.getId());
            preparedStatement.setString(2,u.getName());
            preparedStatement.setString(3,u.getEmail());
            preparedStatement.setString(4,u.getSex());
            preparedStatement.setLong(5,u.getPhone());
            preparedStatement.setString(6,u.getPassword());
            preparedStatement.setString(7,u.getUserName());
            preparedStatement.setDate(8,new Date(System.currentTimeMillis()));
            preparedStatement.setDate(9,new Date(System.currentTimeMillis()));
            preparedStatement.addBatch();
        }
        System.out.println("begin inserting");
        int[] ints = preparedStatement.executeBatch();
        conn.commit();
        System.out.println("finish insert,row:" + ints.length);




    }





}

