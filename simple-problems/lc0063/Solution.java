/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 63.Unique Paths II 
 * @about: 62.Unique Paths
 * 
 * @note: mealy dp
 * implementation
 *    - adding 1 leading column
 *    - set dp[1] = 1, representing initial position is reachable
 *  
 *               0  1  2  3  4  5  6  <- grid.column.index
 *           [0, 1, 0, 0, 0, 0, 0, 0] <- dp.init
 *    row: 0 start ...
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        final int nrow = grid.length;
        final int ncol = grid[0].length;
        // base case: obstacle at corners
        if (grid[0][0] == 1 || grid[nrow - 1][ncol - 1] == 1)
            return 0;

        final int[] dp = new int[ncol + 1];
        dp[1] = 1;
    
        for (int r = 0; r < nrow; ++r) {
            for (int c = 1; c <= ncol; ++c) {
                if (grid[r][c - 1] == 0)
                    dp[c] += dp[c - 1];
                else 
                    dp[c] = 0;
            }
        }
        return dp[ncol];
    }
}
