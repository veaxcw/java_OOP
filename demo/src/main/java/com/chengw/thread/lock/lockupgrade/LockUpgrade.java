package com.chengw.thread.lock.lockupgrade;

import com.chengw.thread.utils.Tools;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author chengwei
 */
public class LockUpgrade {

    public static void main(String[] args) throws NoSuchFieldException, InterruptedException {
        System.out.println("无锁：" + ClassLayout.parseInstance(new Object()).toPrintable());

        Thread.sleep(5000);

        Object object = new Object();

        Thread thread = new Thread(() -> {
            synchronized (object) {
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        });

        thread.start();
        thread.join();
        thread2.start();
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
            Thread.sleep(2000);
        }




    }

}
