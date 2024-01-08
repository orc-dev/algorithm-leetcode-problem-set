/**
 * @author: orc-dev
 * @update: Jan.8 2024
 * 
 * Tag
 *   # dynamic programming (3D)
 * 
 * Impl detail:
 *   - Initialization technique
 *     We can use the wrapped 'Integer' type with the default value 'null' or 
 *     use the 'int' type and manually fill '-1' as the default value. Both 
 *     approaches may have overhead. We opt for the 'int' type and use '0' as 
 *     the default value. Conceptually, we add 1 cherry for each cell, and when
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
        
        return dp(0, 0, ncol - 1) - (nrow * 2);
    }

    /**
     * 
     * @param r - current row index
     * @param c1 - col index of Robot_1
     * @param c2 - col index of Robot_2
     * @return Maximum number of cherries collected when Robot_1 at (r,c1) 
     *         and Robot_2 at (r,c2).
     */
    private int dp(int r, int c1, int c2) {
        // boundary checks
        if (c1 < 0 || c1 == dp[0].length ||
            c2 < 0 || c2 == dp[0].length || r == dp.length) {
            return 0;
        }
        // lookup memo-table
        if (dp[r][c1][c2] > 0) {
            return dp[r][c1][c2];
        }
        // recursive call
        int opt = 0;
        for (int i = c1 - 1; i <= c1 + 1; ++i) {
            for (int j = c2 - 1; j <= c2 + 1; ++j) {
                if (i < j) {
                    opt = Math.max(opt, dp(r + 1, i, j));
                }
            }
        }
        // update memo-table
        dp[r][c1][c2] = opt + grid[r][c1] + grid[r][c2] + 2;
        return dp[r][c1][c2];
    }
}