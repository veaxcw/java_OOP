package com.chengw.thread.utils;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chengw
 */
public class Debug {

    private static ThreadLocal<SimpleDateFormat> sdfWrapper = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        }

    };

    enum Label {
        INFO("INFO"),
        ERR("ERROR");
        String name;

        Label(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }



    public static void info(String format, Object... args) {
        printf(Label.INFO, format, args);
    }

    public static void info(boolean message) {
        info("%s", message);
    }

    public static void info(int message) {
        info("%d", message);
    }

    public static void error(String message, Object... args) {
        printf(Label.ERR, message, args);
    }

    public static void printf(Label label, String format, Object... args) {
        SimpleDateFormat sdf = sdfWrapper.get();
        @SuppressWarnings("resource")
        final PrintStream ps = label == Label.INFO ? System.out : System.err;
        String message = "[" + /*System.nanoTime()*/ sdf.format(new Date()) + "][" + label.getName()
                + "][" + Thread.currentThread().getName() + "]:" + format ;
        ps.println(message);
    }


}
