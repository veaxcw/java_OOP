package com.chengw.thread.cooperation.countDownLatch;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author chengw
 */
public class ServerManager {

    static volatile CountDownLatch latch;
    static Set<Service> services;

    public static void startServices(){


        services = getServices();


        for(Service service:services){
            service.start();
        }
    }

    public static  boolean checkServiceStatus(){
        boolean allIsOk = true;

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Service service:services){
            if(!service.isStarted()){
                allIsOk = false;
                break;
            }
        }

        return allIsOk;
    }
    /**
     * 模拟实际代码
     * */
    static Set<Service> getServices(){
        latch = new CountDownLatch(3);

        HashSet<Service> services = new HashSet<>();
        services.add(new SampleServiceA(latch));
        services.add(new SampleServiceB(latch));
        services.add(new SampleServiceC(latch));
        return services;
    }

}
