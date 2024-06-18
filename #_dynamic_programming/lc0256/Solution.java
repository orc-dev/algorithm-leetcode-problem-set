/**
 * @author: orc-dev
 * @update: Jun.17 2024
 * 
 * @leetcode: 256. Paint House
 * @tag: dp (1D.multi-states)
 * 
 * Performance
 *   - Runtime: O(n) (0 ms)
 *   - Memory: O(1)
 */
class Solution {
    public int minCost(int[][] costs) {
        return dp(costs, 0, 0, 0, 0);
    }

    private int dp(int[][] costs, int i, int R, int B, int G) {
        if (i == costs.length) {
            return Math.min(R, Math.min(B, G));
        }
        return dp(costs, i + 1,
            costs[i][0] + Math.min(B, G),
            costs[i][1] + Math.min(R, G),
            costs[i][2] + Math.min(R, B)
        );
    }
}
