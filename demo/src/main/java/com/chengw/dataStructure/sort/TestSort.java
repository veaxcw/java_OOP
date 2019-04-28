package com.chengw.dataStructure.sort;

import org.junit.jupiter.api.Test;

public class TestSort {

    @Test
    public void Test(){
        Thread bumbleThread = new Thread(new BumbleThread());
        Thread insertThread = new Thread(new InsertSortThread());

        bumbleThread.start();
        insertThread.start();

    }
}
