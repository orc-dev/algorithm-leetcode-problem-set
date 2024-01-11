/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 53. Maximum Subarray
 * @tag: #[dp]
 * 
 * Log
 *   - mealy | multi-layer | linear
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int acc = 0;
        for (int x : nums) {
            acc += x;
            max = Math.max(max, acc);
            // if partial sum 'acc' is non-positive, reset it to 0
            acc = Math.max(acc, 0);  
        }
       return max; 
    }
}