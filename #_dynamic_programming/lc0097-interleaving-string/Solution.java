/**
 * @author: orc-dev
 * @update: Jan.11 2024
 * 
 * @leetcode: 97. Interleaving String
 * @tag: dp (mealy)
 * 
 * dp.recursion
 *   - if [TOP] is true, check foo:zip match
 *   - if [LSH] is true, check bar:zip match
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..) 
 *   * Follow up requirement achieves: use O(s2.length) extra memory.
 */
class Solution {

    /**
     * dp.tabular
     *               1  2  3  4  5  6  <- dp.dimension.j (stable)
     *               -  0  1  2  3  4  <- bar.index
     *               "  a  a  b  c  c  <- bar.chars  
     *           [F][T][F][F][F][F][F] <- dp.init.[bar.length + 2]
     *   0  -  " [F][ ][ ][ ][ ][ ][ ]
     *   1  0  d [F][ ][ ][ ][ ][ ][ ]
     *   2  1  b [F][ ][ ][ ][ ][ ][ ]
     *   3  2  b [F][ ][ ][ ][ ][ ][ ]
     *   4  3  c [F][ ][ ][ ][ ][ ][ ]
     *   5  4  a [F][ ][ ][ ][ ][ ][*] <- dp.return
     *   |  |  |
     *   |  |  +-- foo.chars
     *   |  +-- foo.index
     *   +-- dp.dimension.i (transient)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        final char[] foo = s1.toCharArray();
        final char[] bar = s2.toCharArray();
        final char[] zip = s3.toCharArray();
        // Precheck length
        if (foo.length + bar.length != zip.length) {
            return false;
        }
        // dp.init
        final boolean[] dp = new boolean[bar.length + 2];
        dp[1] = true;

        // dp.iteration
        for (int i = 0; i <= foo.length; ++i) {
            for (int j = 1; j < dp.length; ++j) {
                final int k = i + j - 2;
                // dp.recursion
                dp[j] = dp[j]     && match(foo, i - 1, zip, k) ||
                        dp[j - 1] && match(bar, j - 2, zip, k);
            }
        }
        return dp[dp.length - 1];
    }

    /** Returns true if two chars are both empty or equal. */
    private boolean match(char[] child, int c, char[] parent, int p) {
        // Two empty strings match
        if (c < 0 && p < 0) {
            return true;
        }
        // Two non-empty chars match
        return (c >= 0) && (p >= 0) && (child[c] == parent[p]);
    }
}