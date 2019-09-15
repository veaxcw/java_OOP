package com.chengw.proxy.dynamicproxy;


import com.chengw.proxy.dynamicproxy.impl.Target;

import java.lang.reflect.Proxy;

/**
 * @author chengw
 */
public class Test {

    public static void main(String[] args) {
        Target target = new Target();

        Operation proxy = (Operation) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new TargetProxy(target));

        proxy.say();

    }

}
