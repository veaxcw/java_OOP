package com.chengw.designpattern.proxy;

import com.chengw.designpattern.proxy.dao.ProxyDao;
import com.chengw.designpattern.proxy.dao.support.ProxyDaoImpl;

/**
 * @author chengw
 */
public class ProxyTest {

    private JdkProxyFactory jdkProxyFactory = new JdkProxyFactory();

    public  void test() {
        ProxyDaoImpl target = new ProxyDaoImpl();
        jdkProxyFactory.setTarget(target);
        ProxyDao proxy = (ProxyDao) jdkProxyFactory.getProxyInstance();
        proxy.insert();


    }

    public static void main(String[] args) {


        new ProxyTest().test();
    }

}
