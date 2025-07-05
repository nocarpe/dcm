package com.dcm.thread.alg.d31;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * @author : yyyao
 * @date : 2025/6/7
 * @description :
 **/
public class RepeatZifu {


    public int lengthOfLongestSubstring99(String s) {
        int len = s.length();
        int[] allIndex = new int[128];
        for (int i = 0; i < 128; i++) {
            allIndex[i] = -1;
        }

        int result = 0;
        int start = 0;
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i);
            start = Math.max(start, allIndex[idx] + 1);
            result = Math.max(result, i - start + 1);
            allIndex[idx] = i;
        }
        return result;

    }


    public String longestPalindrome99(String s) {

        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            i = findLongest(i, str, range);
        }
        return s.substring(range[0], range[1] + 1);

    }

    private int findLongest(int low, char[] str, int[] range) {

        int high = low;

        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        int ans = high;

        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        //record
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;

    }

    //最小覆盖子串
    //给一个字符串s,一个字符串t,返回s中涵盖t所有字符的最小子串。如果s中不存在涵盖t所有字符的子串，则返回空字符串“”。

    /**
     * 这段代码是解决 **"最小覆盖子串"（Minimum Window Substring）** 问题的滑动窗口（Sliding Window）实现。给定字符串 `s` 和 `t`，它要在 `s` 中找到一个最短的子串，使得这个子串包含 `t` 中的所有字符（包括重复字符）。
     *
     * ---
     *
     * ## **代码解析**
     * ### **1. 初始化哈希表**
     * ```java
     * int[] hash = new int[128];  // ASCII 码范围
     * for (char ch : chart) {
     * hash[ch]--;  // 初始化 t 的字符计数为负数
     * }
     * ```
     * - `hash` 数组用于统计字符出现次数：
     * - 对 `t` 中的字符 `ch`，`hash[ch]--` 表示需要匹配的字符数（初始为负数）。
     * - 例如，`t = "AABC"`，则 `hash['A'] = -2`，`hash['B'] = -1`，`hash['C'] = -1`。
     *
     * ### **2. 滑动窗口遍历**
     * ```java
     * for (int i = 0, j = 0, cnt = 0; i < n; i++) {
     * hash[chars[i]]++;  // 右指针 i 右移，增加当前字符计数
     * if (hash[chars[i]] <= 0) {
     * cnt++;  // 如果当前字符是 t 中的字符，且尚未满足要求，cnt++
     * }
     * while (cnt == m && hash[chars[j]] > 0) {
     * hash[chars[j++]]--;  // 左指针 j 右移，减少字符计数
     * }
     * if (cnt == m) {
     * if (res.isEmpty() || res.length() > i - j + 1) {
     * res = s.substring(j, i + 1);  // 更新最小窗口
     * }
     * }
     * }
     * ```
     * - **`i`（右指针）**：扩展窗口，逐个增加字符计数。
     * - **`j`（左指针）**：收缩窗口，尝试找到更小的有效窗口。
     * - **`cnt`**：记录当前窗口中已经匹配 `t` 中字符的个数，当 `cnt == m` 时，说明窗口已覆盖 `t` 的所有字符。
     *
     * #### **关键逻辑**
     * 1. **右移 `i`（扩展窗口）**：
     * - `hash[chars[i]]++`：增加当前字符的计数。
     * - 如果 `hash[chars[i]] <= 0`，说明这个字符是 `t` 中需要的，`cnt++`。
     *
     * 2. **右移 `j`（收缩窗口）**：
     * - 当 `cnt == m`（窗口已覆盖 `t` 的所有字符），尝试收缩窗口：
     * - 如果 `hash[chars[j]] > 0`，说明 `chars[j]` 在窗口中多出现了，可以右移 `j` 并减少计数。
     * - 最终，`j` 会停在最小的有效窗口起始位置。
     *
     * 3. **更新最小窗口**：
     * - 如果当前窗口比之前记录的 `res` 更小，更新 `res`。
     *
     * ---
     *
     * ## **示例**
     * 假设：
     * - `s = "ADOBECODEBANC"`
     * - `t = "ABC"`
     *
     * **执行过程**：
     * 1. `i` 遍历 `s`，当 `i` 指向 `'C'`（`"ADOBEC"`）时，`cnt == 3`，窗口 `"ADOBEC"` 覆盖 `t`。
     * - 收缩 `j`，`j` 移动到 `'B'`（`"OBEC"` 仍覆盖 `t`），最终 `j` 停在 `'B'`（`"BEC"` 不满足，回退到 `"ADOBEC"`）。
     * - 记录 `res = "ADOBEC"`（长度 6）。
     *
     * 2. 继续遍历，`i` 指向 `'B'`（`"ADOBECODEBA"`），`cnt == 3`，收缩 `j` 到 `'O'`（`"ODEBANC"`），最终 `j` 停在 `'C'`（`"CODEBA"` 不满足，回退到 `"ODEBANC"`）。
     * - 不更新 `res`（长度 7 > 6）。
     *
     * 3. `i` 指向 `'C'`（`"ADOBECODEBANC"`），`cnt == 3`，收缩 `j` 到 `'B'`（`"BANC"`），满足条件。
     * - 更新 `res = "BANC"`（长度 4）。
     *
     * 最终返回 `"BANC"`。
     *
     * ---
     *
     * ## **总结**
     * - **时间复杂度**：`O(n)`，`i` 和 `j` 各遍历一次 `s`。
     * - **空间复杂度**：`O(1)`，`hash` 数组固定大小（128）。
     * - **核心思想**：滑动窗口 + 哈希计数，动态调整窗口大小，确保覆盖 `t` 的所有字符，并记录最小窗口。
     * * @param s
     */
    public String minWindow(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int n = chars.length;
        int m = chart.length;

        int[] hash = new int[128];
        for (char ch : chart) {
            hash[ch]--;
        }
        String res = "";
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            hash[chars[i]]++;
            if (hash[chars[i]] <= 0) {
                cnt++;
            }
            while (cnt == m && hash[chars[j]] > 0) {
                hash[chars[j++]]--;
            }
            if (cnt == m) {
                if (res.isEmpty() || res.length() > i - j + 1) {
                    res = s.substring(j, i + 1);
                }
            }
        }
        return res;

    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || Objects.equals(strs[0], "")) {
            return "";
        }
        int len = strs.length;
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (i > strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }

        }

        return strs[0];

    }

    //股票买卖

    public int maipaigupiao(int[] nums) {

        int res = 0;
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            temp = Math.min(temp, nums[i]);
            res = Math.max(res, nums[i] - temp);
        }

        return res;
    }


    public List<List<Integer>> permuteBack(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        Deque<Integer> stacks = new ArrayDeque<>();

        dfs(nums, res, used, stacks, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, boolean[] used, Deque<Integer> temp, int i) {

        if (i == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (used[j]) {
                continue;
            }
            temp.offer(nums[j]);
            used[j] = true;
            dfs(nums, res, used, temp, i + 1);
            temp.removeLast();

        }
    }


    public int findTotal(int[] nums) {
        int res = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(curSum, curSum + nums[i]);
            res = Math.max(res, curSum);
        }
        return res;

    }

    public int[] fastSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int i, int j) {

        if (i > j) {
            return;
        }

        int base = nums[i];

        int l = i, q = j;

        while (l != q) {
            while (nums[q] >= base && l < q) {
                q--;
            }
            while (nums[l] <= base && l < q) {
                l++;
            }
            if (l < q) {
                int temp = nums[q];
                nums[q] = nums[l];
                nums[l] = temp;
            }
        }
        nums[i] = nums[l];
        nums[j] = base;

        sort(nums, i, l - 1);
        sort(nums, l + 1, j);

    }

    //end


    public String huiwenzichuang(String str) {
        String result = "";

        char[] c = str.toCharArray();
        int[] idx = new int[2];
        for (int i = 0; i < str.length(); i++) {
            i = iteratorStr(c, idx, i);
        }

        return str.substring(idx[0], idx[1] + 1);
    }

    private int iteratorStr(char[] c, int[] idx, int low) {
        int high = low;
        while (high < c.length - 1 && c[high + 1] == c[high]) {
            high--;
        }
        int ans = high;
        while (low > 0 && high < c.length - 1 && c[low - 1] == c[high + 1]) {
            low--;
            high++;
        }
        if (high - low > idx[1] - idx[0]) {
            idx[0] = low;
            idx[1] = high;
        }
        return ans;
    }


}
