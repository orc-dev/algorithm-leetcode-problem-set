/**
 * @author: orc-dev
 * @update: Jan.24 2024
 * 
 * @leetcode: 1143. Longest Common Subsequence
 * @tag: dp(mealy)
 * 
 * @log:
 *              q
 *              |
 *        | z | y |
 *   p -- | x | * |
 * 
 *   dp.* = dp.z + 1         (if p match q)
 *   dp.* = max(dp.y, dy.x)  (otherwise)
 * 
 * @implementation:
 *   Space-saving implementation with some ugly variable-swap statments.
 * 
 * Performance
 *   - Runtime: (7 ms)
 *   - Memory: O(n)
 */
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        final char[] foo = text1.toCharArray();
        final char[] bar = text2.toCharArray();
        final int m = foo.length;
        final int n = bar.length;
        final int[] dp = new int[n + 1];

        for (int i = 0; i < m; ++i) {
            // dp[0] a padding variable meanwhile storing temp variable
            dp[0] = 0;
            for (int j = 1; j <= n; ++j) {
                // dp.recursion
                if (foo[i] == bar[j - 1]) {
                    dp[j] = (1 + dp[0]) | ((dp[0] = dp[j]) & 0);
                }
                else {
                    dp[j] = Math.max(dp[j - 1],
                            Math.max(dp[0], dp[0] = dp[j]));
                }
            }
        }
        return dp[n];
    }
}