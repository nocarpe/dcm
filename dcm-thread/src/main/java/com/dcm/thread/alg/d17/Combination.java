package com.dcm.thread.alg.d17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2024/8/28
 * @description :求解组合数
 **/
public class Combination {


    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        difs(nums, ans, temp, target, 0);

        return ans;
    }

    private void difs(int[] nums, List<List<Integer>> ans, List<Integer> temp, int target, int i) {

        if (nums.length == i) {
            return;
        }

        if (target == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        difs(nums, ans, temp, target, i + 1);
        if (target - nums[i] < 0) {
            temp.add(nums[i]);
            difs(nums, ans, temp, target - nums[i], i);
            temp.remove(temp.size() - 1);
        }

    }


}
