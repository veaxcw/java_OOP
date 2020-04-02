package com.chengw.datastructure.quene;

import com.chengw.datastructure.quene.impl.QueueChannel;

/**
 * @author chengwei
 */
public class Test {

    public static void main(String[] args) {
        IChannel<Integer> queue = new QueueChannel<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        while (queue.getSize() > 0) {
            System.out.println(queue.pop());
        }

    }
}
