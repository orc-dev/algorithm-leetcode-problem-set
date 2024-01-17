/**
 * @author: orc-dev
 * @update: Jan.16 2024
 * 
 * @leetcode: 152. Maximum Product Subarray
 * @tag: dp
 * 
 * @log
 *   - non-zero segment processing approach
 *   - dp: continuous temp max and min approach (too much comparison)
 *   - dp: two pass (most intuitive and efficient) *
 */
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        // Forward traversal
        int prod = 1;
        for (int i = 0; i < nums.length; ++i) {
            prod *= nums[i];
            max = Math.max(max, prod);
            if (prod == 0) {
                prod = 1;
            }
        }
        // Backward traversal
        prod = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            prod *= nums[i];
            max = Math.max(max, prod);
            if (prod == 0) {
                prod = 1;
            }
        }
        return max;
    }
}
