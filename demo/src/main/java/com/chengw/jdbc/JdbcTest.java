package com.chengw.jdbc;

import com.chengw.jdbc.po.UserBean;
import com.chengw.jdbc.utils.DataUtils;
import com.chengw.jdbc.utils.JdbcConnectionUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author chengw
 */
public class JdbcTest {

    public static void main(String[] args) {
        Connection connection = JdbcConnectionUtils.getConnection();

        List<UserBean> data = DataUtils.simulateData(100000);

        JdbcConnectionUtils.insetBatch(connection,data);
    }

}
