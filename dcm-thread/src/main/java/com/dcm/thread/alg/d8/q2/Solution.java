package com.dcm.thread.alg.d8.q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2023/6/23
 * @description : 全排列
 **/
public class Solution {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permut(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, track, used);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (!used[i]) {
                used[i] = true;
                track.addLast(nums[i]);
                backtrack(nums, track, used);
                used[i] = false;
                track.removeLast();
            }


        }


    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        System.out.println(new Solution().permut(arr));
    }

    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> permut1(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        recursion(nums, track, used);

        return result;
    }

    private void recursion(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                track.addLast(nums[i]);
                recursion(nums, track, used);
                used[i] = false;
                track.removeLast();
            }

        }

    }


    private void recursion1(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }

            if (!used[i]) {
                used[i] = true;
                track.addLast(nums[i]);
                recursion(nums, track, used);
                used[i] = false;
                track.removeLast();
            }

        }

    }


}
