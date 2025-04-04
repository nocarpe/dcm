package com.dcm.thread.alg.d27;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2025/2/12
 * @description :给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 **/
public class ZiJi {
    List<List<Integer>> result =new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0,nums);
        return result;
    }

    private void dfs(int cur, int[] nums) {
        if(cur == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[cur]);
        dfs(cur+1,nums);
        temp.remove(temp.size()-1);
        dfs(cur+1,nums);
    }


}
