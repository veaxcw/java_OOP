package com.chengw.web.gbn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class GBNTest {

    public static void main(String[] args) {

        GoBackNPacket[] packets = new GoBackNPacket[100];

        for(int i =0;i < 100;i++) {
            packets[i] = new GoBackNPacket();
        }

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), namedThreadFactory);

        GBN gbn = new GBN();
        gbn.sender = packets;
        gbn.total_packet = 100;
        gbn.init();
        gbn.simGoBackN(99);
        namedThreadFactory.newThread(gbn);
        executor.execute(gbn);
    }
}
