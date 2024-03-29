/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 62.Unique Paths
 * @tag: #[dp]
 * 
 * DP tabular representation (moore dp)
 *        0  1  2  3  4  5  6
 *       [1][ ][ ][ ][ ][ ][ ] <-- dp.init
 *     0 [ ][ ][ ][ ][ ][ ][ ]
 *     1 [ ][ ][ ][ ][ ][ ][x]
 *     2 [ ][ ][ ][ ][ ][y][z]
 * 
 * Recursion: dp@z = dp@x + dp@y
 */
class Solution {
    public int uniquePaths(int m, int n) {
        final int[] dp = new int[n];
        dp[0] = 1;
        
        for (int r = 0; r < m; ++r) {
            for (int c = 1; c < n; ++c) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[n - 1];
    }
}
