package com.chengw.jvm.oom.methodAreaOOM;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 运行时常量池OverFlow
 * VM args:-XX:PermSize=10M -XX:MAXPermSize=10M
 *  -XX:PermSize和-XX:MaxPermSize在jdk1.8中被弃用了，
 *  使用-XX:MetaspaceSize和-XX:MaxMetaspaceSize替代。
 *  也就是说，jdk1.8中永久代(Permanent Generation)已被替换为元空间(Metaspace)。
 * 规定永久代大小
 * **/
public class JavaMethodAreaOOM {

    static class OOMObject{

    }
    /**具体我也没有明白**/
    //todo
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,args);
                }
            });
            enhancer.create();
        }
    }

}
