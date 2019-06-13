package com.chengw.dataStructure.quene;

import java.io.Serializable;

/**
 * @author chengw
 * <? extends T> 表示类型的上界，表示参数化类型的可能是T 或是 T的子类
 * <? super T> 表示类型下界（Java Core中叫超类型限定），表示参数化类型是此类型的超类型（父类型），直至Object
 */
public class Queue<T extends Comparable<? super T>> implements Serializable {


    private static final long serialVersionUID = 5708222387757460853L;

    private static final int  MIN_CAPACITY = 10;

    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    private static  final int INCREMENT = 0;

    private Object[] dataElement;

    private int front = 0;

    private int rear = 0;

    private int queueSize = 0;




    public Queue() {
        this(MIN_CAPACITY);
    }

    public Queue(int initialCapacity){
        dataElement = new Object[initialCapacity];
        this.queueSize = initialCapacity;
    }

    public Boolean add(T o){
        if((rear + 1)%queueSize == front){
            throw  new IndexOutOfBoundsException("队列已满");
        }
        dataElement[rear] = o;
        rear = (rear+1)%queueSize;
        return true;
    }

    public  T remove(){
        if(rear == front){
            return null;
        }
        T o = (T) dataElement[front];
        dataElement[front] = null;
        front = (front+1)%queueSize;
        return o;
    }

    public int length(){

        return (rear - front + queueSize)%queueSize;

    }

    public  int size(){
        return  queueSize;
    }


}
