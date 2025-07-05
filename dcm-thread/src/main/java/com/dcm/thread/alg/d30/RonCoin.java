package com.dcm.thread.alg.d30;

/**
 * @author : yyyao
 * @date : 2025/4/5
 * @description :
 **/
public class RonCoin {

    /**
     * 如果只有一间房间，那么金额为nums[0]
     * 如果只有两间房间，那么金额为max(nums[0],nums[1])
     * 如果大于两间房间
     * 1.偷窃第n间房间，那么就不能偷窃第n-1间房间，那么最后的金额为$(n)+$(n-2)
     * 2.不偷窃第n间房间，那么偷窃的总金额就为$(n-1)
     * 所以可得出状态转移方程
     * dp[i] = max(dp[i-2]+num[i],dp[i-1])
     */

    public int rob(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public int rob2(int[] nums){

        if(nums ==null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length ==1){
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0]= nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] =Math.max(dp[i-1],dp[i-2]+nums[i]);
        }


        return dp[length-1];

    }




    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int area = getArea(grid, i, j);
                    res = Math.max(res, area);
                }
            }
        }

        return res;
    }




    public int getArea(int[][] grid, int r, int c) {
        if (!isInArea(grid, r, c)) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return 1 + getArea(grid, r - 1, c) + getArea(grid, r + 1, c) + getArea(grid, r, c - 1) + getArea(grid, r,
            c + 1);

    }

    public boolean isInArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && c >= 0 && c < grid[0].length;
    }


}
