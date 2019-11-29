package com.chengw.utils.listutils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengw
 */
public class ListUtils {

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
             list = source.subList(i * size, Math.min((i + 1) * size, source.size()));
             result.add(list);
        }

        return result;

    }

    /**
     * List 深拷贝
     * @param src 源list
     * @param <T> 泛型对象
     * @return 拷贝后的List
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();

        // todo  一点都不优雅
        out.close();
        byteOut.close();
        in.close();
        byteIn.close();

        return dest;
    }

}
