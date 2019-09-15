package com.chengw.proxy.dynamicproxy;

import com.chengw.proxy.dynamicproxy.impl.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chengw
 */
public class TargetProxy implements InvocationHandler {

    private Target target;

    public TargetProxy(Target target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoking target method......");
        Object o = method.invoke(target, args);
        System.out.println("after invoking target method......");
        return o;
    }
}
