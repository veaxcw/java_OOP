package com.chengw.thread.cooperation.cyclicBarrier;

import com.chengw.thread.utils.Debug;
import com.chengw.thread.utils.Tools;

import java.util.concurrent.BrokenBarrierException;
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
                rank[i][j] = new Soldier(i + 1,j);
            }
        }
        /**
         * 当前排士兵射击完毕后撤离坑
         * **/
        shiftBarrier = new CyclicBarrier(this.n, () -> {
            nextLine = (nextLine + 1)%lastCount;
            Debug.info("Next turn is " + nextLine);
        });

        startBarrier = new CyclicBarrier(this.n);

    }

    public void start() throws InterruptedException {
        Thread[] threads = new Thread[this.n];

        for(int i = 0;i < this.n;i++){
            threads[i] = new Shooting(nextLine,i);
            threads[i].start();
        }

        Thread.sleep(lasting*1000);
        done = true;
        for(Thread t:threads){
            t.join();
        }
        Debug.info("finished");
    }


    /**
     *工作线程
     */
     class Shooting extends Thread{

        final int row;

        final int col;

        Shooting(int row,int col) {
            this.row = row;

            this.col = col;
        }

        @Override
        public void run() {
            Soldier soldier;
            try {

                done = row < 4?false:true;
                while (!done){
                    soldier = rank[row][col];
                    /*ki
                    * 一排中的士兵必须同时射击
                    * **/
                    startBarrier.await();

                    soldier.fire();
                    /***
                     * 一排中的士兵必须等待该排中所有其他士兵射击结束后才能离开把坑
                     * CyclicBarrier中的最后一个线程会去执行 其构造方法中的run方法
                     * ****/
                    shiftBarrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    static class Soldier {

        private final int row;

        private final int col;

        Soldier(int row, int col) {
            this.row = row;
            this.col = col;
        }


        public void fire(){
            Debug.info("第"+ row + "排，第" + col + "个士兵开始射击");
            Tools.randomPause(5000);
            Debug.info("第"+ row + "排，第" + col + "个士兵开始射击");
        }
    }

    public static void main(String[] args) {
        ShootPractice shootPractice = new ShootPractice(4,5,24);
        try {
            shootPractice.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
