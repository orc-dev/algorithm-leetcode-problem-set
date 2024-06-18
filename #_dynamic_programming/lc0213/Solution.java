/**
 * @author: orc-dev
 * @update: Jun.17 2024
 * 
 * @leetcode: 213. House Robber II
 * @tag: dp (1D-multi_states)
 * 
 * Performance
 *   - Runtime: O(n) (0 ms)
 *   - Memory: O(1)
 */
class Solution {
    public int rob(int[] nums) {
        // Special case: only one house
        if (nums.length == 1) {
            return nums[0];
        }
        // General cases (2 and more houses)
        return Math.max(
            rob(nums, 0, nums.length - 1, 0, 0),
            rob(nums, 1, nums.length, 0, 0));
    }

    /** Dynamically update current states from two previous states */
    private int rob(int[] nums, int i, int end, int grab, int skip) {
        if (i == end) {
            return Math.max(grab, skip);
        }
        return rob(nums, i + 1, end, skip + nums[i], Math.max(grab, skip));
    }
}