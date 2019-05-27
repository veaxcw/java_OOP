package com.chengw.designPattern.proxyPattern;

import com.chengw.designPattern.proxyPattern.dao.ProxyDao;
import com.chengw.designPattern.proxyPattern.dao.support.ProxyDaoImpl;

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
