package com.dcm.thread.alg.d29;

import com.dcm.thread.alg.ListNode;
import com.dcm.thread.alg.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import org.apache.logging.log4j.util.Strings;

/**
 * @author : yyyao
 * @date : 2025/3/24
 * @description :
 **/
public class Juzhen {


    public boolean searchMatrix(int[][] matrix, int target) {

        for (int[] row : matrix) {
            int temp = search(row, target);
            if (temp > 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }

        }
        return -1;
    }


    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];

        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < cols; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[rows - 1][cols - 1];
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int i = 0;
        Map<String, Character> map = new HashMap<>();
        StringBuffer stb = new StringBuffer();
        outerLoop:
        // 标签标记外层循环
        while (true) {
            for (int j = 0; j < strs.length; j++) {
                // 检查是否越界（防止 strs[j] 长度不足）
                if (i >= strs[j].length()) {
                    break outerLoop; // 直接跳出外层 while 循环
                }
                char c = strs[j].charAt(i);
                String key = c + "_" + i + "";
                if (j == 0) {
                    map.put(key, c);
                } else if (map.containsKey(key)) {
                    continue;
                } else {
                    break outerLoop;
                }
            }
            // 所有字符匹配时才添加（否则会重复添加）
            stb.append(strs[0].charAt(i));
            i++;
        }
        return stb.toString();
    }


    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> cenxu(TreeNode node) {
        if (node == null) {
            return res;
        }
        cenxudfs(node, 0);
        return res;
    }

    public void cenxudfs(TreeNode node, int num) {
        if (num == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(num).add(node.getVal());
        if (node.getLeft() != null) {
            cenxudfs(node.getLeft(), num + 1);
        }
        if (node.getRight() != null) {
            cenxudfs(node.getRight(), num + 1);
        }


    }


    //二叉树的最大宽度
    public int isPalindrome(TreeNode head) {
        int res = 1;
        List<Pair<TreeNode, Integer>> arr = new ArrayList<>();
        arr.add(new Pair<>(head, 1));

        while (!arr.isEmpty()) {
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : arr) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();

                if (node.getLeft() != null) {
                    tmp.add(new Pair<>(node.getLeft(), index * 2));
                }
                if (node.getRight() != null) {
                    tmp.add(new Pair<>(node.getRight(), index * 2 + 1));
                }
            }
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
        }

        return res;
    }


}
