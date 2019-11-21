package com.chengw.designpattern.proxy.dao.support;

import com.chengw.designpattern.proxy.dao.ProxyDao;

public class ProxyDaoImpl implements ProxyDao {
    @Override
    public void insert() {
        System.out.println("inserting ....");
    }
}
