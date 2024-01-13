/**
 * @author: orc-dev
 * @update: Jan.12 2024
 * 
 * @leetcode: 115. Distinct Subsequences
 * @tag: dp(mealy)
 * 
 * {!} How to organize ITERATION in a most efficient way?
 *     [y][ ]
 *     [x][*]   if dp[*] depends on dp[x] and dp[y]
 */
class Solution {
    /**
     *             0  1  2  3  4  5  6  <- src.index
     *             a  b  c  a  b  c  c  <- src.chars
     *         [1][ ][ ][ ][ ][ ][ ][ ]
     *   0  a  [0][ ][ ][ ][ ][ ][ ][ ]
     *   1  b  [0][ ][ ][ ][ ][ ][y][ ]
     *   2  c  [0][ ][ ][ ][ ][ ][x][*] <- dp.return
     *   |  |   |
     *   |  |   +-- dp.init 
     *   |  |
     *   |  +-- tgt.chars
     *   +-- tgt.index
     * 
     *   dp[*] = dp[x] + charMatch() * dp[y]
     */
    public int numDistinct(String s, String t) {
        final char[] src = s.toCharArray();
        final char[] tgt = t.toCharArray();

        final int[] dp = new int[tgt.length + 1];
        dp[0] = 1;

        for (int i = 0; i < src.length; ++i) {
            for (int j = tgt.length; j > 0; --j) {
                if (src[i] == tgt[j - 1])
                    dp[j] += dp[j - 1];
            }
        }
        return dp[dp.length - 1];
    }
}
