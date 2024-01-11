/**
 * @author: orc-dev
 * @update: Jan.10 2024
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
        for (int r = 0; r < n; ++r) {
            int TL = r;
            dp[0] = r + 1;
            for (int c = 1; c <= m; ++c) {
                // swap
                dp[c] = TL | (TL = dp[c]) & 0;
                // compare
                if (s[c - 1] != t[r]) {
                    dp[c] = 1 + min(dp[c - 1], dp[c], TL);
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