package com.chengw.jdbc;

import com.chengw.jdbc.po.SysUserBean;
import com.chengw.jdbc.utils.DataUtils;
import com.chengw.jdbc.utils.JdbcConnectionUtils;
import com.chengw.utils.listutils.ListUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author chengw
 */
public class JdbcTest {

    public static void main(String[] args) {
        Connection connection = JdbcConnectionUtils.getConnection();

        List<SysUserBean> data =  DataUtils.simulateSysUsers(200);
        List<List<SysUserBean>> lists = ListUtils.listSplit(data, 50);
        int start = 0;
        try {
            for(List<SysUserBean> var:lists) {
                System.out.println("开始插入第"+start+"条-第" + (start + var.size()) + "条");
                JdbcConnectionUtils.insetBatch(connection,var);
                start += var.size();
                System.out.println("插入结束");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
