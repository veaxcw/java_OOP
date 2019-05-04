package com.chengw.jvm.oom.runtimeConstandPoolOverflow;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池OverFlow
 * VM args:-XX:PermSize=10M -XX:MAXPermSize=10M
 * 规定永久代大小
 * **/
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        int i = 0;

        /**此方法在JDK1.6以下会抛出OOM*/
        while(i  < 1){
            /**
             * intern 为Native方法
             * When the intern method is invoked, if the pool already contains a
             *      * string equal to this {@code String} object as determined by
             *      * the {@link #equals(Object)} method, then the string from the pool is
             *      * returned. Otherwise, this {@code String} object is added to the
             *      * pool and a reference to this {@code String} object is returned.
             * */
            list.add(String.valueOf(i++).intern());
        }

        /**
         * 在大于JDK1.7的版本中 结构分别是true,false
         * intern()返回首次出现的实例
         * **/
        String str1 = new StringBuilder("计算机").append("软件").toString();

        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }

}
