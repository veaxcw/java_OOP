package com.chengw.designPattern.proxyPattern.dao.support;

import com.chengw.designPattern.proxyPattern.dao.ProxyDao;

public class ProxyDaoImpl implements ProxyDao {
    @Override
    public void insert() {
        System.out.println("inserting ....");
    }
}
