/**
 * @author: orc-dev
 * @update: Jun.06 2024
 * 
 * @leetcode: 312. Burst Balloons
 * @tag: dp
 * 
 * @log
 *   -- (K.preserve) --
 * 
 *   +------+
 *   | -- * |
 *   |    | |
 *   +------+
 * 
 * Performance
 *   - Runtime: O(n^3), (29 ms)
 *   - Memory: O(n^2)
 */
class Solution {
    public int maxCoins(int[] nums) {
        // data = [1] + nums + [1]
        final int n = nums.length + 2;
        final int[] data = new int[n];
        data[0] = 1;
        data[data.length - 1] = 1;
        for (int i = 0; i < nums.length; ++i) {
            data[i + 1] = nums[i];
        }
        final int[][] dp = new int[n][n];

        // dp.bottom up
        for (int r = n - 2; r > 0; --r) {
            for (int c = r; c < n - 1; ++c) {
                // cache product of the two ending numbers
                final int temp = data[r - 1] * data[c + 1];

                // dp.recursion
                for (int k = r; k <= c; ++k) {
                    dp[r][c] = Math.max(dp[r][c], 
                        dp[r][k - 1] + (temp * data[k]) + dp[k + 1][c]
                    );
                }
            }
        }
        return dp[1][n - 2];
    }
}