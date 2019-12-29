package com.chengw.web.gbn;

class GoBackNPacket {

    boolean on_way; // is packet in transit
    boolean reached_dest; // true if packet reached the destination
    boolean acknowledged; // used by drawing function -false will use packet
    // color -true will use ack color
    boolean packet_ack; // is this packet an ack? if false packet is assumed to
    // be a message
    boolean selected; // true if packet was selected by user false otherwise
    boolean received; // true if packet was received
    boolean out_of_order; // packet arrived out of order and an ack from the
    // base needs to be sent
    int packet_pos; // location of packet in diagram
    int ackFor; // carries the number of the packet the ack is for
    boolean buffered;

    GoBackNPacket() {
        on_way = false;
        selected = false;
        reached_dest = false;
        acknowledged = false;
        packet_ack = true;
        received = false;
        out_of_order = false;
        packet_pos = 0;
        ackFor = 0;
        buffered = false;
    }

    GoBackNPacket(boolean onway, int packetpos, int nextseq) {
        on_way = onway;
        selected = false;
        reached_dest = false;
        acknowledged = false;
        packet_ack = true;
        received = false;
        out_of_order = false;
        packet_pos = packetpos;
        ackFor = nextseq;
        buffered = false;

    }
}
