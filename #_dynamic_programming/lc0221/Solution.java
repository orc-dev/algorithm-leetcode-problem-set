/**
 * @author: orc-dev
 * @update: Jan.15 2024
 * 
 * @leetcode: 221. Maximal Square
 * @tag: dp (mealy, pass-local)
 * 
 * @dp.tabular
 *        0  1  2  3  <- dp.col.index
 *           0  1  2  <- data.col.index
 *       [0][0][0][0] <- dp.init
 *     0 [0][ ][ ][ ]
 *     1 [0][ ][z][y]
 *     2 [0][ ][x][*]
 *     |
 *     |-- dp.row.index
 * 
 * @dp.recursion
 *   dp.* = 0                          if matrix[*] = '0'
 *   dp.* = 1 + min(dp.x, dp.y, dp.z)  else
 */
class Solution {

    public int maximalSquare(char[][] matrix) {
        final int nrow = matrix.length;
        final int ncol = matrix[0].length;
        // dp.init
        int max = 0;
        final int[] dp = new int[ncol + 1];
        // dp.iteration
        for (int r = 0; r < nrow; ++r) {
            int temp = 0;
            for (int c = 1; c < dp.length; ++c) {
                // swap
                temp = dp[c] | (dp[c] = temp) & 0;
                // dp.recursion
                dp[c] = (matrix[r][c - 1] == '0') ? 0
                      : 1 + min(dp[c - 1], dp[c], temp);
                // dp.update.gloabl.max
                max = Math.max(max, dp[c]);
            }
        }
        return max * max;
    }

    /** Returns the min of three integers. */
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
