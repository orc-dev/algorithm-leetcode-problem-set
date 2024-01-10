/**
 * @author: orc-dev
 * @update: Jan.9 2024
 * 
 * Tag
 *   # dynamic programming
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public boolean isMatch(String str, String ptn) {
        final char[] p = ptn.toCharArray();
        final char[] s = str.toCharArray();
        final int m = s.length;

        final boolean[] dp = new boolean[m + 1];
        dp[0] = true;  // set init-empty-string match

        for (int i = 0; i < p.length; ++i) {
            // Case: char + asterisk
            if (i + 1 < p.length && p[i + 1] == '*') {
                // forward traversal
                for (int j = 1; j <= m; ++j) {
                    dp[j] |= dp[j - 1] && (p[i] == s[j - 1] || p[i] == '.');
                }
                i++;  // skip the following '*'
            }
            // Case: single char
            else {
                // backward traversal
                for (int j = m; j >= 1; --j) {
                    dp[j] = dp[j - 1] && (p[i] == s[j - 1] || p[i] == '.');
                }
                dp[0] = false;  // clear init-empty-string match
            }
        }
        return dp[m];
    }
}
