package com.chengw.algorithm.countletters;

import java.util.*;
import java.util.stream.Collectors;

/**
 *统计字符串中 字母的数量 并从多到少输出
 *
 * @author chengwei
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(doCalculate(input));
    }

    private static String doCalculate(String input) {

        Map<String,Integer> map = new TreeMap<>();

        for (int i = 0;i < input.length();i++) {
            char c = input.charAt(i);
            String key = String.valueOf(c);
            if (map.containsKey(key)) {
                Integer count = map.get(key);
                map.put(key,count+1);
            }else {
                map.put(key,1);
            }
        }
        LinkedHashSet<Map.Entry<String, Integer>> collect = map.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> -e.getValue()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,Integer> entry :collect) {
            for (int i = 0;i < entry.getValue();i++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();

    }
}
