package com.chengw.algorithm.findNum;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 从五个给定的数组中挑出符合条件的树，并
 *
 * @author chengwei
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List[] in = new List[5];
        for (int i = 0; i < 5; i++) {
            String input = sc.next();
            String[] split = input.split(",");
            List<Integer> collect = Arrays.stream(split).map(Integer::valueOf).collect(Collectors.toList());
            in[i] = collect;
        }


        List<StringBuilder> r = doCalculate(in);

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }

    }

    private static List<StringBuilder> doCalculate(List[] in) {

        List<StringBuilder> result = new ArrayList<>();


        for (int i = 0; i < in.length; i++) {
            if (i == in.length - 1) {
                break;
            }
            Integer max = i == 0 ? 0 : (Integer) in[i - 1].get(in[i - 1].size() - 1);
            doChoose(in[i], in[i + 1], max);
        }

        for (int a = 0; a < in[0].size(); a++) {
            for (int b = 0; b < in[1].size(); b++) {
                for (int c = 0; c < in[2].size(); c++) {
                    for (int d = 0; d < in[3].size(); d++) {
                        for (int e = 0; e < in[4].size(); e++) {
                            StringBuilder builder = new StringBuilder();
                            builder.append(in[0].get(a)).append(",")
                                    .append(in[1].get(b)).append(",")
                                    .append(in[2].get(c)).append(",")
                                    .append(in[3].get(d)).append(",")
                                    .append(in[4].get(d));
                            result.add(builder);
                        }
                    }
                }
            }
        }

        return result;

    }

    private static void doChoose(List<Integer> first, List<Integer> second, Integer max) {

        for (int i = 0; i < first.size(); i++) {
            Integer f = first.get(i);
            for (int j = 0; j < second.size(); j++) {
                Integer s = second.get(j);
                if (f < s) {
                    continue;
                }
                if (j == second.size() - 1 || f < max) {
                    first.remove(f);
                }
            }
        }

    }
}
