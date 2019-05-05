package com.chengw.dataStructure.stack;

import java.io.Serializable;
import java.util.Arrays;

public class Stack<T extends Comparable<? super T>> implements Serializable {


    private static final long serialVersionUID = 1620997424095288093L;

    private final static int MIN_CAPACITY = 10;

    private final static int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    private Integer modCount = 0;

    private Integer capacityIncrement;

    //初始容量为10,容量增量为0
    public Stack() {
       this(MIN_CAPACITY);
    }

    public Stack(Integer initialCapacity,Integer capacityIncrement){
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;

    }

    public Stack(Integer initialCapacity){
        this(initialCapacity,0);
    }

    private Object[] elementData;

    public Object getTop() {
        return elementData[modCount];
    }
    //入栈
    public synchronized T push(T obj) {
        addElement(obj);
        return obj;
    }

    private synchronized void addElement(T data){
        elementData[modCount] = data;
        this.modCount++;
        ensureCapacityHelper(modCount);
        //todo 容量自增长


    }

    private synchronized void ensureCapacityHelper(Integer modCount){
        if(modCount >= elementData.length){
            grow();
        }
    }

    private void grow(){

        Integer oldCapacity = elementData.length;
        Integer newCapacity = oldCapacity  + (capacityIncrement > 0 ?capacityIncrement:oldCapacity);

        if(newCapacity < MIN_CAPACITY)
            newCapacity = MIN_CAPACITY;
        if(newCapacity > MAX_CAPACITY)
            throw new OutOfMemoryError();
        elementData = Arrays.copyOf(elementData,newCapacity);

    }
    //出栈
    public synchronized T pop( ) {

        return removalElementAt(modCount);
    }

    private T removalElementAt(Integer index){
        if(index > size() || index < 0){
            throw new ArrayIndexOutOfBoundsException(index + ">=" + modCount );
        }
        T data = (T) elementData[modCount];
        elementData[modCount] = null;
        this.modCount--;
        return data;
    }

    public T peek(){

        return elementDataAt(modCount - 1);
    }

    private T elementDataAt(Integer index){
        if(index > size() || index < 0){
            throw new ArrayIndexOutOfBoundsException(index + ">=" + modCount + "或者" + index + "<0");
        }
        T data = (T) elementData[index];
        return data;
    }

    public synchronized Integer  size(){
        return  modCount;
    }



    public Integer getLength() {
        return elementData.length;
    }
}
