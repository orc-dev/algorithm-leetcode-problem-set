import java.util.Arrays;
/**
 * @author: orc-dev
 * @update: Jan.13 2024 | Jun.05 2024
 * 
 * @leetcode: 174. Dungeon Game
 * @tag: dp (mealy)
 * 
 * dp.tabular
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
 *   - dp.* + dungeon.* >= min(dp.B, dp.R)
 * 
 * Performance
 *   - Runtime: O(m * n), (2 ms)
 *   - Memory: O(n)
 */
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        final int nrow = dungeon.length;
        final int ncol = dungeon[0].length;
        
        final int[] dp = new int[ncol + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[ncol - 1] = 1;

        for (int r = nrow - 1; r >= 0; --r) {
            for (int c = ncol - 1; c >= 0; --c) {
                dp[c] = Math.max(
                    1, 
                    Math.min(dp[c], dp[c + 1]) - dungeon[r][c]);
            }
        }
        return dp[0];
    }
}