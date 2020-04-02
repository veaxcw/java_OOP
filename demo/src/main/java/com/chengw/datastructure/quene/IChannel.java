package com.chengw.datastructure.quene;

/**
 * @author chengwei
 */
public interface IChannel<T> {

    /**
     * 弹出Channel 首位的元素
     * @return 首位的元素
     */
    public T pop();

    /**
     * 将元素压入Channel
     * @param o 元素
     */
    public void push(T o);

    /**
     * 返回Channel 大小
     * @return  size
     */
    public Integer getSize();

}
