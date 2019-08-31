package com.chengw.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengw
 */
public class ListSplitUtils {

    /**
     * 将List 拆分成多个大小为size相同的List
     * @param source
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> listSplit(List<T> source,int size){
        List<List<T>> result = new ArrayList<>();
        int number = source.size() / size + 1;
        int s = source.size();

        for(int i = 0;i < number;i++){
            List<T> list = null;
             list = source.subList(i * size, (i + 1) * size > source.size() ? source.size() : (i + 1) * size);
             result.add(list);
        }

        return result;

    }

}
