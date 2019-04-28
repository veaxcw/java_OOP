package com.chengw.algorithm;


import org.junit.jupiter.api.Test;



public class PICaculate {

    static String num = "100000000000000000000";
    public static final long CIRCLE = 1000;
    @Test
    public void mathPI(){
        double sum = 0;
        for(long i = 1; i < CIRCLE; i++){
            sum += (double) 1/(i*i);
            if(i%100000 == 0){
                double pi = Math.sqrt(6*sum);
                System.out.println(pi);
            }
        }


    }
}
