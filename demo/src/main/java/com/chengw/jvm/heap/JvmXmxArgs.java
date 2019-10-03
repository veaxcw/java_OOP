package com.chengw.jvm.heap;

/**
 * 打印最大堆内存
 *
 * @author veax
 */
public class JvmXmxArgs {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("jvm parameter；" + arg);
            System.out.println("max heap memory: -Xmx" +
                    Runtime.getRuntime().maxMemory() /1024/1024 + "M" );
        }
    }

}
