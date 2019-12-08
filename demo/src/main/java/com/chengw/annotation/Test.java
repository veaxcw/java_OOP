package com.chengw.annotation;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author chengwei
 */
public class Test {

    @Hello("hello")
    private String var;

    //@Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Field var = Test.class.getDeclaredField("var");
        Hello annotation = var.getAnnotation(Hello.class);
        System.out.println(annotation.value());
    }

}
