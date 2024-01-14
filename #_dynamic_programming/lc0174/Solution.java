/**
 * @author: orc-dev
 * @update: Jan.13 2024
 * 
 * @leetcode: 174. Dungeon Game
 * @tag: dp (mealy)
 * 
 * dp.tabular
 * 
 *        0  1  2  3  <- dp.col.index
 *     0 [*][R][ ][M] 
 *     1 [B][ ][ ][M] 
 *     2 [ ][ ][ ][M] 
 *     | [M][M][1][M] <- dp.init
 *     |  
 *     +-- dp.row.index
 * 
 * dp.specification
 *   - dp[i]: "minimum entry HP for that cell"
 * 
 * dp.recursion
 *   - dp.* >= 1
 *   - dp.* + dn.* >= min(dp.B, dp.R)
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public int calculateMinimumHP(int[][] dn) {
        final int nrow = dn.length;
        final int ncol = dn[0].length;
        // dp.init
        final int[] minEntryHP = new int[ncol + 1];
        java.util.Arrays.fill(minEntryHP, Integer.MAX_VALUE);
        minEntryHP[ncol - 1] = 1;

        // dp.iteration
        for (int r = nrow - 1; r >= 0; --r) {
            for (int c = ncol - 1; c >= 0; --c) {
                // dp.recursion
                minEntryHP[c] = Math.max(
                    1, 
                    Math.min(minEntryHP[c], minEntryHP[c + 1]) - dn[r][c]
                );
            }
        }
        return minEntryHP[0];
    }
}