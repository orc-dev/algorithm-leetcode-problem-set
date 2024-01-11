/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 64.Minimum Path Sum 
 * @tag: #[dp]
 * 
 * @log (#mealy dp)
 * @impliment: dp.init = [max, 0, max, max, ..., max]
 */
class Solution {
    public int minPathSum(int[][] grid) {
        final int nrow = grid.length;
        final int ncol = grid[0].length;
        final int[] dp = new int[ncol + 1];
        // dp.init
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        // dp.iteration
        for (int r = 0; r < nrow; ++r) {
            for (int c = 1; c <= ncol; ++c) 
            {
                // dp.recursion
                dp[c] = grid[r][c - 1] 
                      + Math.min(dp[c - 1], dp[c]);
            }
        }
        return dp[ncol];
    }
}
