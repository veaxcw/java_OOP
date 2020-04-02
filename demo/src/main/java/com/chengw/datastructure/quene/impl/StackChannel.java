package com.chengw.datastructure.quene.impl;

import com.chengw.datastructure.quene.IChannel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author chengwei
 */
public class StackChannel<T> implements IChannel<T> {

    private ArrayList<T> elements;

    private int tail = getSize() - 1;

    public StackChannel() {
        this.elements = new ArrayList<>();
    }

    @Override
    public T pop() {
        return CollectionUtils.isEmpty(elements) ? null : elements.remove(tail);
    }

    @Override
    public void push(T o) {
        elements.add(o);
    }

    @Override
    public Integer getSize() {
        return elements.size();
    }

}
