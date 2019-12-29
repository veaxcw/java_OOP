package com.chengw.web.gbn;

public class GBNTest {

    public static void main(String[] args) {

        GoBackNPacket[] packets = new GoBackNPacket[100];

        for(int i =0;i < 100;i++) {
            packets[i] = new GoBackNPacket();
        }


        GBN gbn = new GBN();
        gbn.sender = packets;
        gbn.total_packet = 100;
        gbn.simGoBackN(99);
        Thread a = new Thread(gbn);
        a.start();
    }
}
