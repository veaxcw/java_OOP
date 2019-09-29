package com.chengw.jdbc.utils;

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
    private final static String URL = "jdbc:mysql://localhost:3306/demo?" +
            "useUnicode=true&" +
            "characterEncoding=UTF-8&" +
            "allowMultiQueries=true&" +
            "serverTimezone=GMT%2B8";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

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

    /**
     * 批量插入1万条用户测试数据
     ****/
    public static void insetBatch(Connection conn, List<UserBean> userBeans) {

        assert conn != null;

        String sql = "INSERT INTO user( `name`, `code`, `sex`, `create_date`, `create_by`, `update_date`, `update_by`) " +
                "VALUES  (?,?,?,?,?,?,?)";

        try {
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for (UserBean u : userBeans) {
                preparedStatement.setString(1, u.getName());
                preparedStatement.setString(2,u.getCode());
                preparedStatement.setString(3,u.getSex());
                preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
                preparedStatement.setLong(5,-1L);
                preparedStatement.setDate(6,new Date(System.currentTimeMillis()));
                preparedStatement.setLong(7,-1L);
                preparedStatement.addBatch();
            }
            System.out.println("begin inserting");
            int[] ints = preparedStatement.executeBatch();
            conn.commit();
            System.out.println("finish insert,row:" + ints.length);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }





}

