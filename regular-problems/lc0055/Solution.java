/**
 * @author: orc-dev
 * @update: Jan.10 2024 | Jun.05 2024
 * 
 * @leetcode: 55.Jump Game
 * @tag: bfs
 * @see: 45.Jump Game II
 * 
 * Performance
 *   - Runtime: (1 ms)
 *   - Memory: constant
 */
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        return bfs(nums, 0, 0);
    }

    private boolean bfs(int[] nums, int currStart, int currEnd) {
        int nextEnd = currEnd;
        for (int i = currStart; i <= currEnd; ++i) {
            nextEnd = Math.max(nextEnd, i + nums[i]);
            if (nextEnd >= nums.length - 1) {
                return true;
            }
        }
        if (currEnd == nextEnd) {
            return false;
        }
        return bfs(nums, currEnd + 1, nextEnd);
    }
}
