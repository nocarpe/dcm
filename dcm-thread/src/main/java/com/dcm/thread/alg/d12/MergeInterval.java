package com.dcm.thread.alg.d12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2024/1/28
 * @description :合并区间
 **/
public class MergeInterval {


    public static void main(String[] args) {

        System.out.println(

            "54".charAt(1)-'0'
        );

        int[] a1 = new int[]{1, 4};
        int[] a2 = new int[]{2, 3};

        //        int[] a3 = new int[]{10, 12};
        //        int[] a4 = new int[]{11, 18};

        int[][] intervals = new int[][]{a1, a2};
        MergeInterval bean = new MergeInterval();
        int[][] result = bean.merge(intervals);
        for (int[] i : result
        ) {
            for (int a : i) {
                System.out.print(a);
            }
        }

    }

    //不限制数组长度
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] y = intervals[i];
            if (merged.size() == 0) {
                merged.add(y);
                continue;
            }
            int[] cur = merged.get(merged.size() - 1);
            if (cur[cur.length - 1] < y[0]) {
                merged.add(y);
            } else {
                merged.get(merged.size() - 1)[cur.length - 1] = Math.max(cur[cur.length - 1], y[y.length - 1]);
            }

        }

        return merged.toArray(new int[merged.size()][]);
    }

    //官方的 限制数组为2个元素
    public int[][] mergeCode(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}




