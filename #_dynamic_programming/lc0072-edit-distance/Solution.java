/**
 * @author: orc-dev
 * @update: Jan.10 2024 | Jun.05 2024
 * 
 * @leetcode: 72.Edit Distance
 * @tag: #[dynamic programming]
 *     +------------- p
 *     |             ...                
 *     |      | TL | top |
 *     q  ... |left|  *  |
 * 
 * dp.recursion
 *     - dp[*] = TL                      (if p == q)
 *     - dp[*] = min(TL, top, left) + 1  (otherwise)
 * 
 * dp.implementation 
 *     - iteration approach
 *     - space saving: 1D array
 *     - swap topleft and current [!]
 */
class Solution {
    public int minDistance(String word1, String word2) {
        final char[] s = word1.toCharArray();
        final char[] t = word2.toCharArray();
        final int m = s.length;
        final int n = t.length;
        // dp.init
        final int[] dp = new int[m + 1];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = i;
        }
        // dp.iteration
        for (int i = 1; i <= n; ++i) {
            int topLeft = i - 1;
            dp[0] = i;
            for (int j = 1; j <= m; ++j) {
                // swap topLeft and dp[j]
                dp[j] = topLeft | (topLeft = dp[j]) & 0;
                // update dp[j]
                if (s[j - 1] != t[i]) {
                    dp[j] = 1 + min(topLeft, dp[j - 1], dp[j]);
                }
            }
        }
        return dp[m];
    }

    /** Returns the min value among three input integers */
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}