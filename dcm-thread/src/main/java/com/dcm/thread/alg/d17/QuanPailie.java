package com.dcm.thread.alg.d17;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2024/8/28
 * @description :全排列
 **/
public class QuanPailie {


    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        boolean[] used = new boolean[len];
        Deque<Integer> stacks = new ArrayDeque<>();
        difs(nums, len, 0, stacks, used, res);
        return res;
    }

    private static void difs(int[] nums, int len, int i, Deque<Integer> stacks, boolean[] used,
        List<List<Integer>> res) {
        if (i == len) {
            res.add(new ArrayList<>(stacks));
        }
        for (int j = 0; j < len; j++) {
            if (used[j]) {
                continue;
            }
            stacks.add(nums[j]);
            used[j] = true;
            difs(nums, len, j + 1, stacks, used, res);
            stacks.removeLast();
            used[j] = false;
        }


    }


}
