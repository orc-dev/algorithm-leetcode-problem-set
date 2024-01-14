/**
 * @author: orc-dev
 * @update: Jan.13 2024
 * 
 * @leetcode: 123. Best Time to Buy and Sell Stock III
 * @tag: dp (mealy)
 * @see: 188. Best Time to Buy and Sell Stock IV
 * 
 * dp.state.diagram
 *          -price        +price         -price        +price
 *    (INIT) -----> (BUY1) -----> (SELL1) -----> (BUY2) -----> (SELL2)
 *     |__^          |__^          |__^           |__^          |__^
 *    
 * dp.specification
 *   - On the i-th day, dp.state represents the maximum profit can be 
 *     achieved at that state.
 */
class Solution {
    public int maxProfit(int[] prices) {
        int b1 = Integer.MIN_VALUE;
        int s1 = 0;
        int b2 = Integer.MIN_VALUE;
        int s2 = 0;  // max profit with at most 2 transactions

        for (int p : prices) {
            s2 = Math.max(s2, s1 + p);
            b2 = Math.max(b2, s1 - p);
            s1 = Math.max(s1, b1 + p);
            b1 = Math.max(b1, -p);
        }
        return s2;
    }
}