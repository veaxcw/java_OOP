package com.chengw.datastructure.hashmap;

import sun.misc.SharedSecrets;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * hashMap 源码解析
 *
 * @author chengwei
 */
@Deprecated
public class HashMap<K, V> extends AbstractMap<K, V>
        implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;


    /**
     * 初始容量 16（必须是2的n次幂）
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 最大容量，map容量必须小于等于1<<30
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 负载因子
     * 装载因子用来衡量HashMap满的程度。
     * loadFactor的默认值为0.75f。
     * 计算HashMap的实时装载因子的方法为：size/capacity，而不是占用桶的数量去除以capacity。
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 阈值，当链表的长度大于8时
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 当 树中元素的个数小于6时转化成链表
     */
    static final int UNTREcEIFY_THRESHOLD = 6;

    /**
     * *
     * 转红黑树 时 最小容量
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 基本节点，树节点的超类
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        HashMap.Node<K, V> next;

        Node(int hash, K key, V value, HashMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */

    /**
     * 计算key 的的hashcode
     */
    static final int hash(Object key) {
        int h;
        // jkd 1.7

        // jdk 1.8 方法
        // h >>> 16 高位参与异或运算
        // (h = key.hashCode()) ^ (h >>> 16) 高位与低位做异或运算
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Returns x's Class if it is of the form "class C implements
     * Comparable<C>", else null.
     * <p>
     * 如果x 实现了 Comparable 接口 则返回Class
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c;
            Type[] ts, as;
            Type t;
            ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
            {
                return c;
            }
            // 遍历实现的接口
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType) t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                    {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 返回 k.compareTo(x) 的结果
     */
    @SuppressWarnings({"rawtypes", "unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable) k).compareTo(x));
    }

    /**
     * 返回指定容量所对应的 table size 也就是hash 桶数组的大小，
     * 转换成最接近的2的你次幂
     * <p>
     * 比如 cap = 3~4 tableSize = 4
     * cap = 9～16 tableSize = 16
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- 字段 -------------- */

    /**
     * 这就是那个hash 桶，实际上就是一个节点的数组
     */
    transient HashMap.Node<K, V>[] table;

    /**
     * 缓存 hashMap 中的所有条目
     */
    transient Set<Map.Entry<K, V>> entrySet;

    /**
     * hashMap中键值对的数量
     */
    transient int size;

    /**
     * hashMap 结构改变的次数
     * <p>
     * todo 什么是结构改变
     */
    transient int modCount;

    /**
     * 下一次扩容的阈值 ：容量*负载因子
     *
     * @serial
     */
    int threshold;

    /**
     * 负载因子
     *
     * @serial
     */
    final float loadFactor;

    /* ---------------- 公共方法 -------------- */

    /**
     * HashMap 构造器 显式声明初始容量 和负载因子
     *
     * @param initialCapacity 初始容量
     * @param loadFactor      负载因子
     * @throws IllegalArgumentException 如果初始容量为负数或者负载因子小于等于0 则抛异常
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * HashMap 构造器 显式声明初始容量
     * 默认负载因子 为0.75
     *
     * @param initialCapacity 初始容量
     * @throws IllegalArgumentException 如果初始容量为负数 则抛异常
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * HashMap 构造器
     * 默认容量 为16
     * 默认负载因子 为0.75
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * 根据一个指定的map 构造一个新的hashMap
     * <p>
     * 默认负载因子：0.75
     *
     * @param m 指定的map
     * @throws NullPointerException 如果map 为空则抛空指针异常
     */
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    /**
     * 将指定的map 中所有的条目 放到该map中
     *
     * @param m     the map
     * @param evict false 则重新构造map，为true 标识 在该map 的基础上继续插入节点 f
     */
    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        if (s > 0) {
            // 依据map 抽更新构造桶
            if (table == null) {
                // 计算m 的容量
                // loadFactor = size /capacity
                float ft = ((float) s / loadFactor) + 1.0F;
                int t = ((ft < (float) MAXIMUM_CAPACITY) ?
                        (int) ft : MAXIMUM_CAPACITY);
                if (t > threshold) {
                    threshold = tableSizeFor(t);
                }
            } else if (s > threshold) {
                resize();
            }
            // 将m 中的键值 放入this
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                putVal(hash(key), key, value, false, evict);
            }
        }
    }

    /**
     * 返回键值对的的数量
     *
     * @return 键值对的数量
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return 返回map 是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据key 返回指定的value
     *
     * @see #put(Object, Object)
     */
    @Override
    public V get(Object key) {
        HashMap.Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * 实现根据key 和 key的hash 获取指定节点
     *
     * @param hash key 的hash值 ( h = key.hashCode () ^ h >>> 16 )
     * @param key  key
     * @return 返回该节点
     */
    final HashMap.Node<K, V> getNode(int hash, Object key) {
        // 那个桶
        HashMap.Node<K, V>[] tab;
        //  第一个节点 和一个临时变量
        HashMap.Node<K, V> first, e;
        // 桶的大小  c
        int n;
        // key
        K k;
        // (tab = table) != null && (n = tab.length) > 0 哈希桶的非空判断
        // (n - 1) & hash 取模运算 计算桶的位置
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            // 首先检查是不是是不是第一个节点
            // hash 相同 且key 相同
            if (first.hash == hash &&
                    ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }
            // 若不是第一个 则遍历链表或者树 找到为止
            if ((e = first.next) != null) {
                if (first instanceof HashMap.TreeNode) {
                    return ((HashMap.TreeNode<K, V>) first).getTreeNode(hash, key);
                }
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    /**
     * 这个不用解释吧
     */
    @Override
    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }


    /**
     * 顾名思义
     * 具体实现  见 putVal
     *
     * @param key   the key
     * @param value the value
     * @return 返回旧值
     */
    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * put 方法的具体实现
     *
     *  方法 hash &（length - 1）
     * 取模运算
     * 它通过h & (table.length -1)来得到该对象的保存位，
     * 而HashMap底层数组的长度总是2的n次方，这是HashMap在速度上的优化。
     * 当length总是2的n次方时，h& (length-1)运算等价于对length取模，也就是h%length，但是&比%具有更高的效率。
     * eg 7%4 = 3 等价于 7&3（111 & 011 = 3）
     *
     * @param hash         hash for key
     * @param key          the key
     * @param value        the value to put
     * @param onlyIfAbsent 如果为true 则不改变已经存在的值
     * @param evict        如果为false 代表在构造一个map
     * @return 返回旧值
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        // 老套路 一堆临时变量
        HashMap.Node<K, V>[] tab;
        HashMap.Node<K, V> p;
        int n, i;
        // 哈希桶为空则重新整一个
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        // (n - 1) & hash 取模运算 计算桶的位置
        // 该坑位没人 则直接蹲下  则直接把节点丢这里
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        } else {
            // 该坑位有人 首先判断 key 是否相同 相同则覆盖该节点
            HashMap.Node<K, V> e;
            K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k)))) {
                e = p;
            } else if (p instanceof HashMap.TreeNode) {
                // 如果是树节点 则按树节点往后插入值
                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            } else {
                // 不是则按链表往后插入
                // 遍历链表
                for (int binCount = 0; ; ++binCount) {
                    // 尾节点为空 则插入
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 链表的长度 大于阈值则转换成红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        {
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    // 相同则覆盖
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    p = e;
                }
            }
            // 存在该键值对
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                // onlyIfAbsent 为false 则用新值覆盖旧值
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                afterNodeAccess(e);
                return oldValue;
            }
        }
        // 结构改变加1
        ++modCount;
        // size 达到阈值 则扩容
        if (++size > threshold) {
            resize();
        }
        // todo 不知道干嘛的
        afterNodeInsertion(evict);
        return null;
    }

    /**
     * 扩容机制
     *
     * 当 键值对的数量 大于 阈值（负载因子 * hash桶的容量）
     *
     * 比如 hash桶的大小 为100 负载因子为0。75 当插入第76个节点时 会触发扩容
     *
     * @return the table
     */
    final HashMap.Node<K, V>[] resize() {
        // 获取旧的哈希桶及其阈值
        HashMap.Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                // 旧哈希桶的大小 大于 最大容量则直接返回
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY) {
                // 新的容量 为旧的容量的两倍
                // 新的阈值 为旧的阈值的两倍
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) // initial capacity was placed in threshold
        {
            newCap = oldThr;
        } else {
            // 取容量和阈值的初始值
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        // 构建新的哈希桶
        threshold = newThr;
        HashMap.Node<K, V>[] newTab = (HashMap.Node<K, V>[]) new HashMap.Node[newCap];
        table = newTab;
        if (oldTab != null) {
            // 遍历旧的hashmap
            for (int j = 0; j < oldCap; ++j) {
                HashMap.Node<K, V> e;
                if ((e = oldTab[j]) != null) {
                    // 释放内存
                    oldTab[j] = null;
                    if (e.next == null) {
                        // 将e 放到他该放的地方
                        newTab[e.hash & (newCap - 1)] = e;
                    } else if (e instanceof HashMap.TreeNode) {
                        // 红黑树的复制
                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
                    } else {
                        // 链表的复制
                        // 方法比较特殊： 它并没有重新计算元素在数组中的位置
                        // 而是采用了 原始位置加原数组长度的方法计算得到位置

                        // 复制节点位置没发生变化的链表
                        HashMap.Node<K, V> loHead = null, loTail = null;
                        // 复制节点位置发生变化的链表
                        HashMap.Node<K, V> hiHead = null, hiTail = null;
                        HashMap.Node<K, V> next;
                        do {
                            /*********************************************/
                            /**
                             * 注: e本身就是一个链表的节点，它有 自身的值和next(链表的值)，但是因为next值对节点扩容没有帮助，
                             */
                            /*********************************************/
                            next = e.next;
                            /*********************************************/

                            /** 注意：不是(e.hash & (oldCap-1));而是(e.hash & oldCap)
                             (e.hash & oldCap) 得到的是 元素的在数组中的位置是否需要移动,示例如下
                             示例1：
                             e.hash = 10 0000 1010
                             oldCap = 16 0001 0000
                            	 &   = 0	 0000 0000       比较高位的第一位 0
                             旧的位置 0000 1010 & 0000 1111 = 1010
                             新的位置 0000 1010 & 0001 1111 = 1010
                            结论：元素位置在扩容后数组中的位置没有发生改变

                             示例2：
                             e.hash = 17 0001 0001
                             oldCap = 16 0001 0000
                            	 &  = 1	 0001 0000      比较高位的第一位   1
                             旧的位置：0001 0001 & 0000 1111 = 1
                             新的位置：0001 0001 & 0001 1111 = 0001 0001 = 17 = 1 + 16 （原数组位置 + 数组长度）
                            //结论：元素位置在扩容后数组中的位置发生了改变，新的下标位置是原下标位置+原数组长度

                            //参考博文：[Java 1.8中HashMap的resize()方法扩容部分的理解](https://blog.csdn.net/u013494765/article/details/77837338)
                            // 0000 0001->0001 0001
                            /*********************************************/
                            // 复制链表
                            if ((e.hash & oldCap) == 0) {
                                // 如果元素的位置没有发生变化
                                if (loTail == null) {
                                    // 第一次 循环 确认 头节点的为止
                                    loHead = e;
                                } else {
                                    loTail.next = e;
                                }
                                // 第一次进来 loHead=loTail=e
                                // 第二次进来 loHead=e loTail = e.next
                                loTail = e;
                            } else {
                                // 如果元素的位置 发生了变化
                                // 将链表复制到hi
                                if (hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        // 将元素位置未发生变化的链表头节点 置于新哈希桶的相同位置
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        // 将元素位置发生变化了的链表头节点 置于新哈希桶的指定位置（原位置 + 旧哈希桶的容量）
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /**
     * 将桶中指定位置的链表转换成红黑树
     * 桶太小 则扩容
     */
    final void treeifyBin(HashMap.Node<K, V>[] tab, int hash) {
        int n, index;
        HashMap.Node<K, V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) {
            // 哈希桶的大小小于64 则触发扩容
            resize();
        } else if ((e = tab[index = (n - 1) & hash]) != null) {
            // index =（n -1）& hash 计算桶中的位置
            // hd 头节点 tl 尾节点
            HashMap.TreeNode<K, V> hd = null, tl = null;
            // 循环遍历链表，转换成双向链表
            do {
                // 链表节点转换成树节点
                // 为方便理解 p[n] 指原链表中第n个节点，
                // 首次进入循环 hd = tl = p[0]
                // 第二次进入 p[1].pre = p[0] p[0].next = p[1] tl = p[1]
                // 以此类推 构建一个双向链表
                HashMap.TreeNode<K, V> p = replacementTreeNode(e, null);
                if (tl == null) {
                    hd = p;
                } else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null) {
                // 双向链表转成红黑树
                hd.treeify(tab);
            }
        }
    }

    /**
     * 将m 中所有的键值对 放到this 中
     *
     * @param m
     * @throws NullPointerException m 为空 则跑出空指针异常
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        putMapEntries(m, true);
    }

    /**
     * 删除节点
     *
     * @param key the key
     * @return
     */
    @Override
    public V remove(Object key) {
        HashMap.Node<K, V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    /**
     * 实现 删除放方法
     *
     * @param hash       key 的hash值
     * @param key        key
     * @param value
     * @param matchValue 为true 则仅删除值相等的键值对
     * @param movable    if false do not move other nodes while removing 为false 则不移动其他节点
     * @return the node,
     */
    final HashMap.Node<K, V> removeNode(int hash, Object key, Object value,
                                                          boolean matchValue, boolean movable) {
        HashMap.Node<K, V>[] tab;
        // 需要删除节点的pre节点（如果需要删除的节点是头节点，p = node）
        // p = node.pre
        HashMap.Node<K, V> p;
        int n, index;
        // 第一个条件：哈希桶不是空 而且 （index = （n -1）& hash） 位置的节点不为空
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            HashMap.Node<K, V> node = null, e;
            K k;
            V v;
            // 找到匹配的节点: 1,hash 值相同，2，key 相同（引用相同，或者 .equals 结果为ture）
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k)))) {
                node = p;
            } else if ((e = p.next) != null) {
                if (p instanceof HashMap.TreeNode) {
                    // 如果是树节点 则调用树节点的方法找到节点
                    node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
                } else {
                    // 遍历链表直到找到即诶单
                    do {
                        if (e.hash == hash &&
                                ((k = e.key) == key ||
                                        (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            // 删除节点 条件:1,节点不为空，2 （若需要匹配节点value 则 匹配value）
            if (node != null && (!matchValue || (v = node.value) == value ||
                    (value != null && value.equals(v)))) {
                if (node instanceof HashMap.TreeNode) {
                    // 节点为树节点 则按照 树节点的方式 删除即诶单
                    ((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);
                } else if (node == p) {
                    // 如果删除的节点 是头节点 直接 原节点的位置 指向node.next
                    tab[index] = node.next;
                } else {
                    // 如果不是 直接 执行 删除链表节点的方法
                    p.next = node.next;
                }
                // 结构改变 + 1，size 见 1
                ++modCount;
                --size;
                // 后处理
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }

    /**
     * 删除所有键值对
     */
    @Override
    public void clear() {

        // 哈希桶所有坑位置空，让gc 回收
        HashMap.Node<K, V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i) {
                tab[i] = null;
            }
        }
    }

    /**
     * 顾名思义
     */
    @Override
    public boolean containsValue(Object value) {
        HashMap.Node<K, V>[] tab;
        V v;
        if ((tab = table) != null && size > 0) {
            for (int i = 0; i < tab.length; ++i) {
                for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 不解释
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new HashMap.KeySet();
            keySet = ks;

        }
        return ks;
    }

    final class KeySet extends AbstractSet<K> {
        @Override
        public final int size() {
            return size;
        }

        @Override
        public final void clear() {
            HashMap.this.clear();
        }
        @Override
        public final Iterator<K> iterator() {
            return new HashMap.KeyIterator();
        }

        @Override
        public final boolean contains(Object o) {
            return containsKey(o);
        }

        @Override
        public final boolean remove(Object key) {
            return removeNode(hash(key), key, null, false, true) != null;
        }

        @Override
        public final Spliterator<K> spliterator() {
            return new HashMap.KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
        }

        @Override
        public final void forEach(Consumer<? super K> action) {
            HashMap.Node<K, V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            }
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e.key);
                    }
                }
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     *
     * @return 返回 map的value
     */
    @Override
    public Collection<V> values() {
        Collection<V> vs = values;
        if (vs == null) {
            vs = new HashMap.Values();
            values = vs;
        }
        return vs;
    }

    final class Values extends AbstractCollection<Object> {
        @Override
        public final int size() {
            return size;
        }

        @Override
        public final void clear() {
            HashMap.this.clear();
        }

        @Override
        public final Iterator<Object> iterator() {
            return new HashMap.ValueIterator();
        }

        public final boolean contains(Object o) {
            return containsValue(o);
        }

        @Override
        public final Spliterator<Object> spliterator() {
            return new ValueSpliterator<Object, Object>(HashMap.this, 0, -1, 0, 0);
        }

        @Override
        public final void forEach(Consumer<? super Object> action) {
            HashMap.Node<K, V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            }
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e.value);
                    }
                }
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     *
     * 很简单
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es;
        return (es = entrySet) == null ? (entrySet = new HashMap.EntrySet()) : es;
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        @Override
        public final int size() {
            return size;
        }

        @Override
        public final void clear() {
            HashMap.this.clear();
        }

        @Override
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new HashMap.EntryIterator();
        }

        @Override
        public final boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            Object key = e.getKey();
            HashMap.Node<K, V> candidate = getNode(hash(key), key);
            return candidate != null && candidate.equals(e);
        }

        @Override
        public final boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                Object key = e.getKey();
                Object value = e.getValue();
                return removeNode(hash(key), key, value, true, true) != null;
            }
            return false;
        }

        @Override
        public final Spliterator<Map.Entry<K, V>> spliterator() {
            return new HashMap.EntrySpliterator<>(this, 0, -1, 0, 0);
        }

        @Override
        public final void forEach(Consumer<? super Map.Entry<K, V>> action) {
            HashMap.Node<K, V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            }
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e);
                    }
                }
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    // Overrides of JDK8 Map extension methods

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        HashMap.Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return putVal(hash(key), key, value, true, true);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return removeNode(hash(key), key, value, true, true) != null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        HashMap.Node<K, V> e;
        V v;
        if ((e = getNode(hash(key), key)) != null &&
                ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
            e.value = newValue;
            afterNodeAccess(e);
            return true;
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        HashMap.Node<K, V> e;
        if ((e = getNode(hash(key), key)) != null) {
            V oldValue = e.value;
            e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
        return null;
    }

    @Override
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mappingFunction) {
        if (mappingFunction == null) {
            throw new NullPointerException();
        }
        int hash = hash(key);
        HashMap.Node<K, V>[] tab;
        HashMap.Node<K, V> first;
        int n, i;
        int binCount = 0;
        HashMap.TreeNode<K, V> t = null;
        HashMap.Node<K, V> old = null;
        if (size > threshold || (tab = table) == null ||
                (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof HashMap.TreeNode) {
                old = (t = (TreeNode<K, V>) first).getTreeNode(hash, key);
            } else {
                HashMap.Node<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
            V oldValue;
            if (old != null && (oldValue = old.value) != null) {
                afterNodeAccess(old);
                return oldValue;
            }
        }
        V v = mappingFunction.apply(key);
        if (v == null) {
            return null;
        } else if (old != null) {
            old.value = v;
            afterNodeAccess(old);
            return v;
        } else if (t != null) {
            t.putTreeVal(this, tab, hash, key, v);
        } else {
            tab[i] = newNode(hash, key, v, first);
            if (binCount >= TREEIFY_THRESHOLD - 1) {
                treeifyBin(tab, hash);
            }
        }
        ++modCount;
        ++size;
        afterNodeInsertion(true);
        return v;
    }

    @Override
    public V computeIfPresent(K key,
                              BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null) {
            throw new NullPointerException();
        }
        HashMap.Node<K, V> e;
        V oldValue;
        int hash = hash(key);
        if ((e = getNode(hash, key)) != null &&
                (oldValue = e.value) != null) {
            V v = remappingFunction.apply(key, oldValue);
            if (v != null) {
                e.value = v;
                afterNodeAccess(e);
                return v;
            } else {
                removeNode(hash, key, null, false, true);
            }
        }
        return null;
    }

    @Override
    public V compute(K key,
                     BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null)
            throw new NullPointerException();
        int hash = hash(key);
        HashMap.Node<K, V>[] tab;
        HashMap.Node<K, V> first;
        int n, i;
        int binCount = 0;
        HashMap.TreeNode<K, V> t = null;
        HashMap.Node<K, V> old = null;
        if (size > threshold || (tab = table) == null ||
                (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof HashMap.TreeNode) {
                old = (t = (TreeNode<K, V>) first).getTreeNode(hash, key);
            } else {
                HashMap.Node<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        V oldValue = (old == null) ? null : old.value;
        V v = remappingFunction.apply(key, oldValue);
        if (old != null) {
            if (v != null) {
                old.value = v;
                afterNodeAccess(old);
            } else {
                removeNode(hash, key, null, false, true);
            }
        } else if (v != null) {
            if (t != null) {
                t.putTreeVal(this, tab, hash, key, v);
            } else {
                tab[i] = newNode(hash, key, v, first);
                if (binCount >= TREEIFY_THRESHOLD - 1) {
                    treeifyBin(tab, hash);
                }
            }
            ++modCount;
            ++size;
            afterNodeInsertion(true);
        }
        return v;
    }

    @Override
    public V merge(K key, V value,
                   BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (remappingFunction == null) {
            throw new NullPointerException();
        }
        int hash = hash(key);
        HashMap.Node<K, V>[] tab;
        HashMap.Node<K, V> first;
        int n, i;
        int binCount = 0;
        HashMap.TreeNode<K, V> t = null;
        HashMap.Node<K, V> old = null;
        if (size > threshold || (tab = table) == null ||
                (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof HashMap.TreeNode) {
                old = (t = (HashMap.TreeNode<K, V>) first).getTreeNode(hash, key);
            } else {
                HashMap.Node<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        if (old != null) {
            V v;
            if (old.value != null) {
                v = remappingFunction.apply(old.value, value);
            } else {
                v = value;
            }
            if (v != null) {
                old.value = v;
                afterNodeAccess(old);
            } else {
                removeNode(hash, key, null, false, true);
            }
            return v;
        }
        if (value != null) {
            if (t != null) {
                t.putTreeVal(this, tab, hash, key, value);
            } else {
                tab[i] = newNode(hash, key, value, first);
                if (binCount >= TREEIFY_THRESHOLD - 1) {
                    treeifyBin(tab, hash);
                }
            }
            ++modCount;
            ++size;
            afterNodeInsertion(true);
        }
        return value;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        HashMap.Node<K, V>[] tab;
        if (action == null) {
            throw new NullPointerException();
        }
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                    action.accept(e.key, e.value);
                }
            }
            if (modCount != mc) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        HashMap.Node<K, V>[] tab;
        if (function == null) {
            throw new NullPointerException();
        }
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                    e.value = function.apply(e.key, e.value);
                }
            }
            if (modCount != mc) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* ------------------------------------------------------------ */
    // Cloning and serialization

    /**
     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
     * values themselves are not cloned.
     *
     * @return a shallow copy of this map
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        HashMap<K,V> result;
        try {
            result = (HashMap<K,V>) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
        result.reinitialize();
        result.putMapEntries(this, false);
        return result;
    }

    // These methods are also used when serializing HashSets
    final float loadFactor() {
        return loadFactor;
    }

    final int capacity() {
        return (table != null) ? table.length :
                (threshold > 0) ? threshold :
                        DEFAULT_INITIAL_CAPACITY;
    }

    /**
     * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
     * serialize it).
     *
     * @serialData The <i>capacity</i> of the HashMap (the length of the
     * bucket array) is emitted (int), followed by the
     * <i>size</i> (an int, the number of key-value
     * mappings), followed by the key (Object) and value (Object)
     * for each key-value mapping.  The key-value mappings are
     * emitted in no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws IOException {
        int buckets = capacity();
        // Write out the threshold, loadfactor, and any hidden stuff
        s.defaultWriteObject();
        s.writeInt(buckets);
        s.writeInt(size);
        internalWriteEntries(s);
    }

    /**
     * Reconstitutes this map from a stream (that is, deserializes it).
     *
     * @param s the stream
     * @throws ClassNotFoundException if the class of a serialized object
     *                                could not be found
     * @throws IOException            if an I/O error occurs
     */
    private void readObject(java.io.ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        // Read in the threshold (ignored), loadfactor, and any hidden stuff
        s.defaultReadObject();
        reinitialize();
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new InvalidObjectException("Illegal load factor: " +
                    loadFactor);
        }
        s.readInt();                // Read and ignore number of buckets
        int mappings = s.readInt(); // Read number of mappings (size)
        if (mappings < 0) {
            throw new InvalidObjectException("Illegal mappings count: " +
                    mappings);
        } else if (mappings > 0) { // (if zero, use defaults)
            // Size the table using given load factor only if within
            // range of 0.25...4.0
            float lf = Math.min(Math.max(0.25f, loadFactor), 4.0f);
            float fc = (float) mappings / lf + 1.0f;
            int cap = ((fc < DEFAULT_INITIAL_CAPACITY) ?
                    DEFAULT_INITIAL_CAPACITY :
                    (fc >= MAXIMUM_CAPACITY) ?
                            MAXIMUM_CAPACITY :
                            tableSizeFor((int) fc));
            float ft = (float) cap * lf;
            threshold = ((cap < MAXIMUM_CAPACITY && ft < MAXIMUM_CAPACITY) ?
                    (int) ft : Integer.MAX_VALUE);

            // Check Map.Entry[].class since it's the nearest public type to
            // what we're actually creating.
            SharedSecrets.getJavaOISAccess().checkArray(s, Map.Entry[].class, cap);
            @SuppressWarnings({"rawtypes", "unchecked"})
            HashMap.Node<K, V>[] tab = (HashMap.Node<K, V>[]) new HashMap.Node[cap];
            table = tab;

            // Read the keys and values, and put the mappings in the HashMap
            for (int i = 0; i < mappings; i++) {
                @SuppressWarnings("unchecked")
                K key = (K) s.readObject();
                @SuppressWarnings("unchecked")
                V value = (V) s.readObject();
                putVal(hash(key), key, value, false, false);
            }
        }
    }

    /* ------------------------------------------------------------ */
    // iterators

    abstract class HashIterator {
        HashMap.Node<K, V> next;        // next entry to return
        HashMap.Node<K, V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            HashMap.Node<K, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final HashMap.Node<K, V> nextNode() {
            HashMap.Node<K, V>[] t;
            HashMap.Node<K, V> e = next;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (e == null) {
                throw new NoSuchElementException();
            }
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            HashMap.Node<K, V> p = current;
            if (p == null) {
                throw new IllegalStateException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }

    final class KeyIterator extends HashMap.HashIterator
            implements Iterator<K> {
        @Override
        public final K next() {
            return (K) nextNode().key;
        }
    }

    final class ValueIterator extends HashMap.HashIterator
            implements Iterator<V> {
        @Override
        public final V next() {
            return (V) nextNode().value;
        }
    }

    final class EntryIterator extends HashMap.HashIterator
            implements Iterator<Map.Entry<K, V>> {
        @Override
        public final Map.Entry<K, V> next() {
            return nextNode();
        }
    }

    /* ------------------------------------------------------------ */
    // spliterators

    static class HashMapSpliterator<K, V> {
        final HashMap<K,V> map;
        HashMap.Node<K, V> current;          // current node
        int index;                  // current index, modified on advance/split
        int fence;                  // one past last index
        int est;                    // size estimate
        int expectedModCount;       // for comodification checks

        HashMapSpliterator(HashMap<K,V> m, int origin,
                           int fence, int est,
                           int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence() { // initialize fence and size on first use
            int hi;
            if ((hi = fence) < 0) {
                HashMap<K,V> m = map;
                est = m.size;
                expectedModCount = m.modCount;
                HashMap.Node<K, V>[] tab = m.table;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            return hi;
        }

        public final long estimateSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    static final class KeySpliterator<K, V>
            extends HashMap.HashMapSpliterator<K, V>
            implements Spliterator<K> {
        KeySpliterator(HashMap<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public HashMap.KeySpliterator<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new HashMap.KeySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        @Override
        public void forEachRemaining(Consumer<? super K> action) {
            int i, hi, mc;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap<K,V> m = map;
            HashMap.Node<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else {
                mc = expectedModCount;
            }
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                HashMap.Node<K, V> p = current;
                current = null;
                do {
                    if (p == null) {
                        p = tab[i++];
                    } else {
                        action.accept(p.key);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super K> action) {
            int hi;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap.Node<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null) {
                        current = tab[index++];
                    } else {
                        K k = current.key;
                        current = current.next;
                        action.accept(k);
                        if (map.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    static final class ValueSpliterator<K, V>
            extends HashMap.HashMapSpliterator<K, V>
            implements Spliterator<V> {
        ValueSpliterator(HashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public HashMap.ValueSpliterator<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new HashMap.ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        @Override
        public void forEachRemaining(Consumer<? super V> action) {
            int i, hi, mc;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap<K,V> m = map;
            HashMap.Node<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else {
                mc = expectedModCount;
            }
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                HashMap.Node<K, V> p = current;
                current = null;
                do {
                    if (p == null) {
                        p = tab[i++];
                    } else {
                        action.accept(p.value);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap.Node<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null) {
                        current = tab[index++];
                    } else {
                        V v = current.value;
                        current = current.next;
                        action.accept(v);
                        if (map.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    static final class EntrySpliterator<K, V>
            extends HashMap.HashMapSpliterator<K, V>
            implements Spliterator<Map.Entry<K, V>> {
        EntrySpliterator(HashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public HashMap.EntrySpliterator<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new HashMap.EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        @Override
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            int i, hi, mc;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap<K,V> m = map;
            HashMap.Node<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else {
                mc = expectedModCount;
            }
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                HashMap.Node<K, V> p = current;
                current = null;
                do {
                    if (p == null) {
                        p = tab[i++];
                    } else {
                        action.accept(p);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            int hi;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap.Node<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null) {
                        current = tab[index++];
                    } else {
                        HashMap.Node<K, V> e = current;
                        current = current.next;
                        action.accept(e);
                        if (map.modCount == expectedModCount) {
                            return true;
                        }
                        throw new ConcurrentModificationException();
                    }
                }
            }
            return false;
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    /* ------------------------------------------------------------ */
    // LinkedHashMap support


    /*
     * The following package-protected methods are designed to be
     * overridden by LinkedHashMap, but not by any other subclass.
     * Nearly all other internal methods are also package-protected
     * but are declared final, so can be used by LinkedHashMap, view
     * classes, and HashSet.
     */

    // Create a regular (non-tree) node
    Node<K, V> newNode(int hash, K key, V value, HashMap.Node<K, V> next) {
        return new HashMap.Node<>(hash, key, value, next);
    }

    // For conversion from TreeNodes to plain nodes
    HashMap.Node<K, V> replacementNode(HashMap.Node<K, V> p, HashMap.Node<K, V> next) {
        return new HashMap.Node<>(p.hash, p.key, p.value, next);
    }

    // Create a tree bin node
    HashMap.TreeNode<K, V> newTreeNode(int hash, K key, V value, HashMap.Node<K, V> next) {
        return new HashMap.TreeNode<>(hash, key, value, next);
    }

    // For treeifyBin
    HashMap.TreeNode<K, V> replacementTreeNode(HashMap.Node<K, V> p, HashMap.Node<K, V> next) {
        return new HashMap.TreeNode<>(p.hash, p.key, p.value, next);
    }

    /**
     * Reset to initial default state.  Called by clone and readObject.
     */
    void reinitialize() {
        table = null;
        entrySet = null;
        keySet = null;
        values = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }

    // Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(HashMap.Node<K, V> p) {
    }

    void afterNodeInsertion(boolean evict) {
    }

    void afterNodeRemoval(HashMap.Node<K, V> p) {
    }

    // Called only from writeObject, to ensure compatible ordering.
    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
        HashMap.Node<K, V>[] tab;
        if (size > 0 && (tab = table) != null) {
            for (int i = 0; i < tab.length; ++i) {
                for (HashMap.Node<K, V> e = tab[i]; e != null; e = e.next) {
                    s.writeObject(e.key);
                    s.writeObject(e.value);
                }
            }
        }
    }

    /* ------------------------------------------------------------ */
    // Tree bins

    /**
     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
     * extends Node) so can be used as extension of either regular or
     * linked node.
     */
    static final class TreeNode<K, V> extends HashMap.Node<K, V> {
        HashMap.TreeNode<K, V> parent;  // red-black tree links
        HashMap.TreeNode<K, V> left;
        HashMap.TreeNode<K, V> right;
        HashMap.TreeNode<K, V> prev;    // needed to unlink next upon deletion
        boolean red;

        TreeNode(int hash, K key, V val, HashMap.Node<K, V> next) {
            super(hash, key, val, next);
        }

        /**
         * Returns root of tree containing this node.
         */
        final HashMap.TreeNode<K, V> root() {
            for (HashMap.TreeNode<K, V> r = this, p; ; ) {
                if ((p = r.parent) == null) {
                    return r;
                }
                r = p;
            }
        }

        /**
         * Ensures that the given root is the first node of its bin.
         */
        static <K, V> void moveRootToFront(HashMap.Node<K, V>[] tab, HashMap.TreeNode<K, V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                HashMap.TreeNode<K, V> first = (HashMap.TreeNode<K, V>) tab[index];
                if (root != first) {
                    HashMap.Node<K, V> rn;
                    tab[index] = root;
                    HashMap.TreeNode<K, V> rp = root.prev;
                    if ((rn = root.next) != null) {
                        ((TreeNode<K, V>) rn).prev = rp;
                    }
                    if (rp != null) {
                        rp.next = rn;
                    }
                    if (first != null) {
                        first.prev = root;
                    }
                    root.next = first;
                    root.prev = null;
                }
                assert checkInvariants(root);
            }
        }

        /**
         * Finds the node starting at root p with the given hash and key.
         * The kc argument caches comparableClassFor(key) upon first use
         * comparing keys.
         */
        final HashMap.TreeNode<K, V> find(int h, Object k, Class<?> kc) {
            HashMap.TreeNode<K, V> p = this;
            do {
                int ph, dir;
                K pk;
                HashMap.TreeNode<K, V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h) {
                    p = pl;
                } else if (ph < h) {
                    p = pr;
                } else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                } else if (pl == null) {
                    p = pr;
                } else if (pr == null) {
                    p = pl;
                } else if ((kc != null ||
                        (kc = comparableClassFor(k)) != null) &&
                        (dir = compareComparables(kc, k, pk)) != 0) {
                    p = (dir < 0) ? pl : pr;
                } else if ((q = pr.find(h, k, kc)) != null) {
                    return q;
                } else {
                    p = pl;
                }
            } while (p != null);
            return null;
        }

        /**
         * Calls find for root node.
         */
        final HashMap.TreeNode<K, V> getTreeNode(int h, Object k) {
            return ((parent != null) ? root() : this).find(h, k, null);
        }

        /**
         * Tie-breaking utility for ordering insertions when equal
         * hashCodes and non-comparable. We don't require a total
         * order, just a consistent insertion rule to maintain
         * equivalence across rebalancings. Tie-breaking further than
         * necessary simplifies testing a bit.
         */
        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a == null || b == null ||
                    (d = a.getClass().getName().
                            compareTo(b.getClass().getName())) == 0) {
                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                        -1 : 1);
            }
            return d;
        }

        /**
         * Forms tree of the nodes linked from this node.
         */
        final void treeify(HashMap.Node<K, V>[] tab) {
            HashMap.TreeNode<K, V> root = null;
            for (HashMap.TreeNode<K, V> x = this, next; x != null; x = next) {
                next = (HashMap.TreeNode<K, V>) x.next;
                x.left = x.right = null;
                if (root == null) {
                    x.parent = null;
                    x.red = false;
                    root = x;
                } else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (HashMap.TreeNode<K, V> p = root; ; ) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h) {
                            dir = -1;
                        } else if (ph < h) {
                            dir = 1;
                        } else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                                (dir = compareComparables(kc, k, pk)) == 0) {
                            dir = tieBreakOrder(k, pk);
                        }

                        HashMap.TreeNode<K, V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0) {
                                xp.left = x;
                            } else {
                                xp.right = x;
                            }
                            root = balanceInsertion(root, x);
                            break;
                        }
                    }
                }
            }
            moveRootToFront(tab, root);
        }

        /**
         * Returns a list of non-TreeNodes replacing those linked from
         * this node.
         */
        final HashMap.Node<K, V> untreeify(HashMap<K,V> map) {
            HashMap.Node<K, V> hd = null, tl = null;
            for (HashMap.Node<K, V> q = this; q != null; q = q.next) {
                HashMap.Node<K, V> p = map.replacementNode(q, null);
                if (tl == null) {
                    hd = p;
                } else {
                    tl.next = p;
                }
                tl = p;
            }
            return hd;
        }

        /**
         * Tree version of putVal.
         */
        final HashMap.TreeNode<K, V> putTreeVal(HashMap<K, V> map,
                                                                  HashMap.Node<K, V>[] tab,
                                                                  int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            HashMap.TreeNode<K, V> root = (parent != null) ? root() : this;
            for (HashMap.TreeNode<K, V> p = root; ; ) {
                int dir, ph;
                K pk;
                if ((ph = p.hash) > h) {
                    dir = -1;
                } else if (ph < h) {
                    dir = 1;
                } else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                } else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {
                        HashMap.TreeNode<K, V> q, ch;
                        searched = true;
                        if (((ch = p.left) != null &&
                                (q = ch.find(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.find(h, k, kc)) != null)) {
                            return q;
                        }
                    }
                    dir = tieBreakOrder(k, pk);
                }

                HashMap.TreeNode<K, V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    HashMap.Node<K, V> xpn = xp.next;
                    HashMap.TreeNode<K, V> x = map.newTreeNode(h, k, v, xpn);
                    if (dir <= 0) {
                        xp.left = x;
                    } else {
                        xp.right = x;
                    }
                    xp.next = x;
                    x.parent = x.prev = xp;
                    if (xpn != null) {
                        ((TreeNode<K, V>) xpn).prev = x;
                    }
                    moveRootToFront(tab, balanceInsertion(root, x));
                    return null;
                }
            }
        }

        /**
         * Removes the given node, that must be present before this call.
         * This is messier than typical red-black deletion code because we
         * cannot swap the contents of an interior node with a leaf
         * successor that is pinned by "next" pointers that are accessible
         * independently during traversal. So instead we swap the tree
         * linkages. If the current tree appears to have too few nodes,
         * the bin is converted back to a plain bin. (The test triggers
         * somewhere between 2 and 6 nodes, depending on tree structure).
         */
        final void removeTreeNode(HashMap<K, V> map, HashMap.Node<K, V>[] tab,
                                  boolean movable) {
            int n;
            if (tab == null || (n = tab.length) == 0) {
                return;
            }
            int index = (n - 1) & hash;
            HashMap.TreeNode<K, V> first = (HashMap.TreeNode<K, V>) tab[index], root = first, rl;
            HashMap.TreeNode<K, V> succ = (HashMap.TreeNode<K, V>) next, pred = prev;
            if (pred == null) {
                tab[index] = first = succ;
            } else {
                pred.next = succ;
            }
            if (succ != null) {
                succ.prev = pred;
            }
            if (first == null) {
                return;
            }
            if (root.parent != null) {
                root = root.root();
            }
            if
            (root == null
                    || (movable
                    && (root.right == null
                    || (rl = root.left) == null
                    || rl.left == null))) {
                tab[index] = first.untreeify(map);  // too small
                return;
            }
            HashMap.TreeNode<K, V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null) {
                HashMap.TreeNode<K, V> s = pr, sl;
                while ((sl = s.left) != null) // find successor
                {
                    s = sl;
                }
                boolean c = s.red;
                s.red = p.red;
                p.red = c; // swap colors
                HashMap.TreeNode<K, V> sr = s.right;
                HashMap.TreeNode<K, V> pp = p.parent;
                if (s == pr) { // p was s's direct parent
                    p.parent = s;
                    s.right = p;
                } else {
                    HashMap.TreeNode<K, V> sp = s.parent;
                    if ((p.parent = sp) != null) {
                        if (s == sp.left) {
                            sp.left = p;
                        } else {
                            sp.right = p;
                        }
                    }
                    if ((s.right = pr) != null) {
                        pr.parent = s;
                    }
                }
                p.left = null;
                if ((p.right = sr) != null) {
                    sr.parent = p;
                }
                if ((s.left = pl) != null) {
                    pl.parent = s;
                }
                if ((s.parent = pp) == null) {
                    root = s;
                } else if (p == pp.left) {
                    pp.left = s;
                } else {
                    pp.right = s;
                }
                if (sr != null) {
                    replacement = sr;
                } else {
                    replacement = p;
                }
            } else if (pl != null) {
                replacement = pl;
            } else if (pr != null) {
                replacement = pr;
            } else {
                replacement = p;
            }
            if (replacement != p) {
                HashMap.TreeNode<K, V> pp = replacement.parent = p.parent;
                if (pp == null) {
                    root = replacement;
                } else if (p == pp.left) {
                    pp.left = replacement;
                } else {
                    pp.right = replacement;
                }
                p.left = p.right = p.parent = null;
            }

            HashMap.TreeNode<K, V> r = p.red ? root : balanceDeletion(root, replacement);

            if (replacement == p) {  // detach
                HashMap.TreeNode<K, V> pp = p.parent;
                p.parent = null;
                if (pp != null) {
                    if (p == pp.left) {
                        pp.left = null;
                    } else if (p == pp.right) {
                        pp.right = null;
                    }
                }
            }
            if (movable) {
                moveRootToFront(tab, r);
            }
        }

        /**
         * Splits nodes in a tree bin into lower and upper tree bins,
         * or untreeifies if now too small. Called only from resize;
         * see above discussion about split bits and indices.
         *
         * @param map   the map
         * @param tab   the table for recording bin heads
         * @param index the index of the table being split
         * @param bit   the bit of hash to split on
         */
        final void split(HashMap<K, V> map, HashMap.Node<K, V>[] tab, int index, int bit) {
            HashMap.TreeNode<K, V> b = this;
            // Relink into lo and hi lists, preserving order
            HashMap.TreeNode<K, V> loHead = null, loTail = null;
            HashMap.TreeNode<K, V> hiHead = null, hiTail = null;
            int lc = 0, hc = 0;
            for (HashMap.TreeNode<K, V> e = b, next; e != null; e = next) {
                next = (HashMap.TreeNode<K, V>) e.next;
                e.next = null;
                if ((e.hash & bit) == 0) {
                    if ((e.prev = loTail) == null) {
                        loHead = e;
                    } else {
                        loTail.next = e;
                    }
                    loTail = e;
                    ++lc;
                } else {
                    if ((e.prev = hiTail) == null) {
                        hiHead = e;
                    } else {
                        hiTail.next = e;
                    }
                    hiTail = e;
                    ++hc;
                }
            }

            if (loHead != null) {
                if (lc <= UNTREEIFY_THRESHOLD) {
                    tab[index] = loHead.untreeify(map);
                } else {
                    tab[index] = loHead;
                    if (hiHead != null) // (else is already treeified)
                    {
                        loHead.treeify(tab);
                    }
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD) {
                    tab[index + bit] = hiHead.untreeify(map);
                } else {
                    tab[index + bit] = hiHead;
                    if (loHead != null) {
                        hiHead.treeify(tab);
                    }
                }
            }
        }

        /* ------------------------------------------------------------ */
        // Red-black tree methods, all adapted from CLR

        static <K, V> HashMap.TreeNode<K, V> rotateLeft(HashMap.TreeNode<K, V> root,
                                                        HashMap.TreeNode<K, V> p) {
            HashMap.TreeNode<K, V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null) {
                    rl.parent = p;
                }
                if ((pp = r.parent = p.parent) == null) {
                    (root = r).red = false;
                } else if (pp.left == p) {
                    pp.left = r;
                } else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        static <K, V> HashMap.TreeNode<K, V> rotateRight(HashMap.TreeNode<K, V> root,
                                                         HashMap.TreeNode<K, V> p) {
            HashMap.TreeNode<K, V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null) {
                    lr.parent = p;
                }
                if ((pp = l.parent = p.parent) == null) {
                    (root = l).red = false;
                } else if (pp.right == p) {
                    pp.right = l;
                } else {
                    pp.left = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        static <K, V> HashMap.TreeNode<K, V> balanceInsertion(HashMap.TreeNode<K, V> root,
                                                              HashMap.TreeNode<K, V> x) {
            x.red = true;
            for (HashMap.TreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (!xp.red || (xpp = xp.parent) == null) {
                    return root;
                }
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.right) {
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                } else {
                    if (xppl != null && xppl.red) {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.left) {
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        static <K, V> HashMap.TreeNode<K, V> balanceDeletion(HashMap.TreeNode<K, V> root,
                                                             HashMap.TreeNode<K, V> x) {
            for (HashMap.TreeNode<K, V> xp, xpl, xpr; ; ) {
                if (x == null || x == root) {
                    return root;
                } else if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (x.red) {
                    x.red = false;
                    return root;
                } else if ((xpl = xp.left) == x) {
                    if ((xpr = xp.right) != null && xpr.red) {
                        xpr.red = false;
                        xp.red = true;
                        root = rotateLeft(root, xp);
                        xpr = (xp = x.parent) == null ? null : xp.right;
                    }
                    if (xpr == null) {
                        x = xp;
                    } else {
                        HashMap.TreeNode<K, V> sl = xpr.left, sr = xpr.right;
                        if ((sr == null || !sr.red) &&
                                (sl == null || !sl.red)) {
                            xpr.red = true;
                            x = xp;
                        } else {
                            if (sr == null || !sr.red) {
                                if (sl != null) {
                                    sl.red = false;
                                }
                                xpr.red = true;
                                root = rotateRight(root, xpr);
                                xpr = (xp = x.parent) == null ?
                                        null : xp.right;
                            }
                            if (xpr != null) {
                                xpr.red = (xp == null) ? false : xp.red;
                                if ((sr = xpr.right) != null) {
                                    sr.red = false;
                                }
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateLeft(root, xp);
                            }
                            x = root;
                        }
                    }
                } else { // symmetric
                    if (xpl != null && xpl.red) {
                        xpl.red = false;
                        xp.red = true;
                        root = rotateRight(root, xp);
                        xpl = (xp = x.parent) == null ? null : xp.left;
                    }
                    if (xpl == null) {
                        x = xp;
                    } else {
                        HashMap.TreeNode<K, V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) &&
                                (sr == null || !sr.red)) {
                            xpl.red = true;
                            x = xp;
                        } else {
                            if (sl == null || !sl.red) {
                                if (sr != null) {
                                    sr.red = false;
                                }
                                xpl.red = true;
                                root = rotateLeft(root, xpl);
                                xpl = (xp = x.parent) == null ?
                                        null : xp.left;
                            }
                            if (xpl != null) {
                                xpl.red = (xp != null) && xp.red;
                                if ((sl = xpl.left) != null) {
                                    sl.red = false;
                                }
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateRight(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }
        }

        /**
         * Recursive invariant check
         */
        static <K, V> boolean checkInvariants(HashMap.TreeNode<K, V> t) {
            HashMap.TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right,
                    tb = t.prev, tn = (HashMap.TreeNode<K, V>) t.next;
            if (tb != null && tb.next != t) {
                return false;
            }
            if (tn != null && tn.prev != t) {
                return false;
            }
            if (tp != null && t != tp.left && t != tp.right) {
                return false;
            }
            if (tl != null && (tl.parent != t || tl.hash > t.hash)) {
                return false;
            }
            if (tr != null && (tr.parent != t || tr.hash < t.hash)) {
                return false;
            }
            if (t.red && tl != null && tl.red && tr != null && tr.red) {
                return false;
            }
            if (tl != null && !checkInvariants(tl)) {
                return false;
            }
            if (tr != null && !checkInvariants(tr)) {
                return false;
            }
            return true;
        }
    }

}
