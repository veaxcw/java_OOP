package com.chengw.algorithm.linklist.myStack;

import java.util.Arrays;

/**
 * @author chengw
 */
public class MyStack {


    private static final int INIT_CAPACITY = 10;
    private Integer[] queue  = new Integer[INIT_CAPACITY];
    private volatile int length = 0;

    /** Initialize your data structure here. */
    public MyStack() {
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if(length > queue.length){
            grow();
        }
        queue[length++] = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(empty()){
            return  -1;
        }
        Integer result = queue[--length];
        queue[length] = null;
        return result;
    }

    /** Get the top element. */
    public int top() {
        if(empty()){
            return  -1;
        }
        Integer result = queue[length - 1];
        return result;
    }

    private void grow(){
        int newCapacity = queue.length + INIT_CAPACITY;
         queue = Arrays.copyOf(queue, newCapacity);
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return length == 0?true:false;
    }

}
