/**
 * @author: orc-dev
 * @update: Jan.15 2024 | Jun.17 2024
 * 
 * @leetcode: 221. Maximal Square
 * @tag: dp (mealy, pass-local)
 * 
 * @dp.tabular
 *     [z][y]
 *     [x][*]
 * 
 *   dp.* = 0                 if matrix[*] = '0'
 *   dp.* = 1 + min(x, y, z)  else
 * 
 * @log
 *   - insert padding column at the end
 * 
 * Performance
 *   - Runtime: O(m * n) (8 ms)
 *   - Memory: O(n)
 */
class Solution {
    public int maximalSquare(char[][] matrix) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[] dp = new int[n + 1];

        int max = 0;
        for (int i = 0; i < m; ++i) {
            dp[n] = 0;
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == '0') {
                    dp[j] = 0;
                    continue;
                }
                // swap
                dp[j] = dp[n] | (dp[n] = dp[j]) & 0;
                // dp.recursion
                dp[j] = 1 + Math.min(dp[n], Math.min(dp[j], dp[j + 1]));
                max = Math.max(max, dp[j]);
            }
        }
        return max * max;
    }
}