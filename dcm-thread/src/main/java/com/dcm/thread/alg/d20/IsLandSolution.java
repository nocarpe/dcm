package com.dcm.thread.alg.d20;

/**
 * @author : yyyao
 * @date : 2024/12/19
 * @description :
 **/
public class IsLandSolution {


    void dfs(int[][] grid, int r, int c) {

        if (!inArea(grid, c, r)) {
            return;
        }
        if (grid[r][c] != 1) {
            return;
        }
        grid[r][c] = 2;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }

    private boolean inArea(int[][] grid, int c, int r) { return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }


    public int maxAreaOfIsLand(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int temp = getArea(grid, i, j);
                    res = Math.max(res, temp);
                }
            }
        }
        return res;
    }

    int getArea(int[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return 1 + getArea(grid, r - 1, c) + getArea(grid, r + 1, c) + getArea(grid, r, c - 1) + getArea(grid, r,
            c + 1);
    }


}
