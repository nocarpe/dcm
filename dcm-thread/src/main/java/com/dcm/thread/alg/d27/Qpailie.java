package com.dcm.thread.alg.d27;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2025/2/16
 * @description :
 **/
public class Qpailie {


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        boolean[] used = new boolean[len];
        Deque<Integer> stacks = new ArrayDeque<>();
        difs(nums, len, 0, stacks, used, res);
        return res;
    }

    public void difs(int[] nums, int len, int num, Deque<Integer> stacks, boolean[] used, List<List<Integer>> res) {
        if (num == len) {
            res.add(new ArrayList<>(stacks));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            stacks.addLast(nums[i]);
            used[i] = true;
            difs(nums, len, num + 1, stacks, used, res);
            stacks.removeLast();
            used[i] = false;

        }

    }

}
