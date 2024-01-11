/**
 * @author: orc-dev
 * @update: Jan.8 2024
 * 
 * @leetcode: 1463.Cherry Pickup II
 * @tag: dp (high-d, mealy)
 * 
 * Impl detail:
 *   - Initialization technique
 *     We can use the wrapped 'Integer' type with the default value 'null' or 
 *     use the 'int' type and manually fill '-1' as the default value. Both 
 *     approaches may have overhead. We opt for the 'int' type and use '0' as 
 *     the default value. Conceptually, we add 1 cherry for each row, and when
 *     calculating the final result, we minus these virtually added cherries.
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    private int[][][] dp;  // dp memo-table
    private int[][] grid;  // data

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        final int nrow = grid.length;
        final int ncol = grid[0].length;
        dp = new int[nrow][ncol][ncol];
        
        return dp(0, 0, ncol - 1) - (nrow);
    }

    /**
     * 
     * @param r - current row index
     * @param x - col index of Robot_1
     * @param y - col index of Robot_2
     * @return Maximum number of cherries collected when Robot_1 at (r,c1) 
     *         and Robot_2 at (r,c2).
     */
    private int dp(int r, int x, int y) {
        // boundary checks
        if (x < 0 || x == dp[0].length ||
            y < 0 || y == dp[0].length || r == dp.length) {
            return 0;
        }
        // lookup memo-table
        if (dp[r][x][y] > 0) {
            return dp[r][x][y];
        }
        // recursive call
        int opt = 0;
        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                if (i < j) {
                    opt = Math.max(opt, dp(r + 1, i, j));
                }
            }
        }
        // update memo-table
        return dp[r][x][y] = opt + grid[r][x] + grid[r][y] + 1;
    }
}