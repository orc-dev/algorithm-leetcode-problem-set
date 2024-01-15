/**
 * @author: orc-dev
 * @update: Jan.15 2024
 * 
 * @leetcode: 309. Best Time to Buy and Sell Stock with Cooldown
 * @tag: dp (mealy, state.diagram)
 * 
 * @dp.state.diagram
 *                   -price          +price    
 *      +--> buyable ------> holding ------> cooldown
 *      |     ^__|            ^__|               |
 *      +----------------------------------------+
 * 
 * buyable: the max profit achieved whlie in 'buyable' state on the i-th day
 * holding: the max profit achieved whlie in 'holding' state on the i-th day
 * cooldown: the max profit achieved whlie in 'cooldown' state on the i-th day
 */
class Solution {
    public int maxProfit(int[] prices) {
        int buyable  = 0;
        int holding  = Integer.MIN_VALUE;
        int cooldown = 0;
        
        for (int p : prices) {
            final int temp = cooldown;
            cooldown = holding + p;
            holding = Math.max(holding, buyable - p);
            buyable = Math.max(buyable, temp);
        }
        return Math.max(buyable, cooldown);
    }
}