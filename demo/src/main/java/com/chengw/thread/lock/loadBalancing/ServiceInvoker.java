package com.chengw.thread.lock.loadBalancing;


/**
 * 要求1：支持多种负载均衡算法
 * 要求2：支持系统运行过程中动态调整负载均衡算法
 * 要求3：调用下游部件的过程中，下游部件中非在线主机，需要被排除在外
 * 要求3：下游部件的节点信息可动态调整
 * **/
public class ServiceInvoker {

    private static final ServiceInvoker INSTANCE = new ServiceInvoker();

    private ServiceInvoker(){}

    public static  ServiceInvoker getInstance(){
        return INSTANCE;
    }




}
