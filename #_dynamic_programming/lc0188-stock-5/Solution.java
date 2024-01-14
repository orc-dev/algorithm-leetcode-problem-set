/**
 * @author: ord-dev
 * @update: Jan.13 2024
 * 
 * @leetcode: 188. Best Time to Buy and Sell Stock IV
 * @tag: dp
 * @see: 123. Best Time to Buy and Sell Stock III
 */
class Solution {
    public int maxProfit(int k, int[] prices) {
        // { sk, bk, ... s1, b1, s0 }
        int[] pf = new int[2 * k + 1];
        for (int i = 1; i < pf.length; i += 2) {
            pf[i] = Integer.MIN_VALUE;
        }
        
        for (int p : prices) {
            int sign = 1;
            for (int i = 0; i < pf.length - 1; ++i) {
                pf[i] = Math.max(pf[i], pf[i + 1] + sign * p);
                sign = ~(sign - 1);  // 'sign' oscillates between 1 and -1
            }
        }
        return pf[0];
    }
}
