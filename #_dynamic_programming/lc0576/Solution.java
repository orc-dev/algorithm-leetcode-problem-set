/**
 * @author: orc-dev
 * @update: Jan.25 2024
 * 
 * @leetcode: 576. Out of Boundary Paths
 * @tag: dp(moore)
 * 
 * @log
 *  The non-space-saving recursive method runs faster.
 *  Take care of the order of different edge case checking.
 * 
 * Performance
 *   - Runtime: (2 ms)
 *   - Memory: O(m * n * move)
 */
class Solution {
    private Integer[][][] dp;
    private static int MOD = (int) 1e9 + 7;

    public int findPaths(int m, int n, int maxMove, int initR, int initC) {
        dp = new Integer[m][n][maxMove];
        return dp(initR, initC, maxMove - 1);
    }

    private int dp(int x, int y, int move) {
        if (x < 0 || x == dp.length || y < 0 || y == dp[0].length) {
            return 1;
        }
        if (move < 0) {
            return 0;
        }
        if (dp[x][y][move] != null) {
            return dp[x][y][move];
        }
        final int prev = move - 1;
        final int sum1 = dp(x + 1, y, prev) + dp(x, y + 1, prev);
        final int sum2 = dp(x - 1, y, prev) + dp(x, y - 1, prev);
        
        return dp[x][y][move] = (sum1 % MOD + sum2 % MOD) % MOD;
    }
}