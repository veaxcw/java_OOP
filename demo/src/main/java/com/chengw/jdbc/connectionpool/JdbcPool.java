package com.chengw.jdbc.connectionpool;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcPool implements DataSource {

    private static LinkedList<Connection> listConnections = new LinkedList<Connection>();

    static {
        //加载jdbc.properties
        InputStream inputStream = JdbcPool.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
            String driver = prop.getProperty("driverClass");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("initialSize"));
            Class.forName(driver);
            for(int i = 0; i < jdbcPoolInitSize ; i++){
                Connection connection = DriverManager.getConnection(url,username,password);
                System.out.println("获取到了链接" + connection);
                listConnections.add(connection);
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(listConnections.size() > 0){
            //从连接池中获取一个链接
            final  Connection connection = listConnections.removeFirst();

            return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(),
                    connection.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if(!method.getName().equals("close")) {
                                return  method.invoke(args);
                            } else {
                                listConnections.add(connection);
                            }
                            return null;
                        }
                    });
        }else {
            throw new RuntimeException("等着吧");
        }

    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
