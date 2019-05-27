package com.chengw.designPattern.proxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chengw
 */
public class JdkProxyFactory {

    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    public JdkProxyFactory() {
    }

    public Object getProxyInstance(){
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), (proxy1, method, args) -> {
                    /**something before invoke*/
                    method.invoke(target,args);
                    /**something after invoke**/
                    return proxy1;
                });
        return proxy;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

}
