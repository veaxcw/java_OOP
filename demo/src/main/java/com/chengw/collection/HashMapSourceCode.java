package com.chengw.collection;

import java.util.HashMap;

public class HashMapSourceCode {

   /* final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        HashMap.Node<K,V>[] tab; //这个就是那个桶 一个entry数组
        HashMap.Node<K,V> p; //桶中间的元素
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;//如果是个空桶 就重新分配一个

        if ((p = tab[i = (n - 1) & hash]) == null)//计算这个桶数组的下标
            tab[i] = newNode(hash, key, value, null);
        else {//为了解决hash冲突
            HashMap.Node<K,V> e;
            K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof HashMap.TreeNode)
                e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }*/
}
