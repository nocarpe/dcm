package com.dcm.thread.alg.d27;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2025/2/23
 * @description :
 **/
public class Pailie {


    public List<List<Integer>> pailie(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int idx = 0;
        boolean[] used = new boolean[len];
        Deque<Integer> deque = new LinkedList<>();
        dfs(res, idx, len, used, deque, nums);
        return res;
    }

    private void dfs(List<List<Integer>> res, int idx, int len, boolean[] used, Deque<Integer> deque, int[] nums) {
        if (idx == len) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            deque.addLast(nums[i]);
            used[i] = true;
            dfs(res, idx + 1, len, used, deque, nums);
            deque.removeLast();
            used[i] = false;
        }


    }
}
