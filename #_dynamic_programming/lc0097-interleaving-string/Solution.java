/**
 * @author: orc-dev
 * @update: Jan.11 2024 | Jun.06 2024
 * 
 * @leetcode: 97. Interleaving String
 * @tag: dp (mealy)
 * 
 *         e  x  a  m  p  l  e
 *     [T][ ][ ][ ][ ][ ][ ][ ]
 *   c [ ][ ][ ][ ][ ][ ][ ][ ]
 *   a [ ][ ][ ][ ][ ][ ][ ][ ]
 *   t [ ][ ][ ][ ][ ][ ][ ][?]
 * 
 * Performance
 *   - Runtime: O(m * n) (0 ms, 2D dp table recursion)
 *   - Memory: O(m * n)
 */
class Solution {
    private char[] M, N, T;
    private Boolean[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {
        M = s1.toCharArray();
        N = s2.toCharArray();
        T = s3.toCharArray();
        final int m = M.length;
        final int n = N.length;
        // lengths match check
        if (m + n != T.length) {
            return false;
        }
        dp = new Boolean[m + 1][n + 1];
        dp[0][0] = true;
        return dp(m, n);
    }

    /** Returns if s3[0:i+j] can be formed by s1[0:i] and s2[0:j] */
    private boolean dp(int i, int j) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        // compare chars first, then shortcut subsequent dp calls
        return dp[i][j] = 
            (i > 0) && (M[i - 1] == T[i + j - 1]) && dp(i - 1, j) || 
            (j > 0) && (N[j - 1] == T[i + j - 1]) && dp(i, j - 1);
    }
}