/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 55.Jump Game
 * @about: 45.Jump Game II
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public boolean canJump(int[] nums) {
        // No need to check base case of length 1
        return bfs(nums, 0, 0, 0);
    }

    /** Use BFS to check reachability to the end of 'nums' */
    private boolean bfs(int[] nums, int i, int currEnd, int nextEnd) {
        // Case where 'next level is empty'
        if (i > currEnd)
            return false;
        
        nextEnd = Math.max(nextEnd, i + nums[i]);
        if (nextEnd >= nums.length - 1)
            return true;
        
        if (i < currEnd)
            return bfs(nums, i + 1, currEnd, nextEnd);
        else
            return bfs(nums, i + 1, nextEnd, nextEnd);
    }
}
