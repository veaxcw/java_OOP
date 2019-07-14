package com.chengw.thread.cooperation.cyclicBarrier;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;
import javafx.scene.paint.CycleMethod;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author veax
 */
public class ShootPractice {

    /**
     * 士兵队列
     * **/
    final Soldier[][] rank;

    /**
     * 每排士兵的数量
     * **/
    final int n;

    /**
     * 持续时间
     * ****/
    final int lasting;

    volatile boolean done = false;

    volatile int nextLine = 0;

    final   CyclicBarrier shiftBarrier;
    final CyclicBarrier startBarrier;

    public ShootPractice(int n,final int lastCount,int lasting) {

        this.n = n;
        this.lasting = lasting;
        this.rank = new Soldier[n][lastCount];

        for(int i = 0; i < n;i++){
            for(int j = 0;j < lastCount;j++){
                rank[i][j] = new Soldier(i * n + j);
            }
        }

        shiftBarrier = new CyclicBarrier(this.n, () -> {
            nextLine = (++nextLine)%lastCount;
            Debug.info("Next turn is " + nextLine);
        });

        startBarrier = new CyclicBarrier(this.n);

    }


    static class Soldier {

        private final int seqNo;

        public Soldier(int seqNo) {
            this.seqNo = seqNo;
        }

        public void fire(){
            Debug.info("start to fire");
            Tools.randomPause(5000);
            Debug.info("finish fire");
        }
    }



}
