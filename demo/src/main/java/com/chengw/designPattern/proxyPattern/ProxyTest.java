package com.chengw.designPattern.proxyPattern;

import com.chengw.designPattern.proxyPattern.dao.ProxyDao;
import com.chengw.designPattern.proxyPattern.dao.support.ProxyDaoImpl;

public class ProxyTest {

    private JDKProxyFactory jdkProxyFactory = new JDKProxyFactory();

    public  void test() {
        ProxyDaoImpl target = new ProxyDaoImpl();
        jdkProxyFactory.setTarget(target);
        ProxyDao proxy = (ProxyDao) jdkProxyFactory.getProxyInstance();
        proxy.insert();


    }

    public static void main(String[] args) {

//        ProxyDao target = new ProxyDaoImpl();
//        InvocationHandler invocationHandler = new InvocationHandlerImpl(target);
//        ProxyDao proxy = (ProxyDao) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),invocationHandler);
//        proxy.insert();

        new ProxyTest().test();
        //System.out.println("0000");
    }

}
