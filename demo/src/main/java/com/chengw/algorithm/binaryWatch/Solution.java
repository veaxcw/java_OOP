package com.chengw.algorithm.binaryWatch;

import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 例如，上面的二进制手表读取 “3:25”。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 案例:
 *
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *  
 *
 * 注意事项:
 *
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 *
 * @author chengw
 */
public class Solution {

    byte[] bytes = new byte[10];

    public List<String> readBinaryWatch(int num) {

//todo
        return null;

    }

    private String cal(byte[] bytes){

        int hour = 0;

        int min = 0;

        for(int i = 3;i >= 0;i--){
            hour += Math.pow(2,i - 3)*bytes[i];
        }

        for(int i = 9;i >=4;i--){
            min += Math.pow(2,i - 9)*bytes[i];
        }

//        return hour + ":"  + min < 10?(;
        //todo
        return null;
    }



}
