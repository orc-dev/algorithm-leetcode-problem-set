/**
 * @author: orc-dev
 * @update: Jan.10 2024 | Jun.05 2024
 * 
 * @leetcode: 45.Jump Game II
 * @tag: bfs
 * 
 * Performance:
 *   - Runtime: (0 ms)
 *   - Memory: constant
 */
class Solution {
    public int jump(int[] nums) {
        // Check if we can reach the end within current level
        if (nums.length == 1) {
            return 0;
        }
        return bfs(nums, 0, 0, 0);
    }

    /**
     * An recursive implementation of BFS algorithm, where we only use 'nextEnd'
     * as a checking parameter for termination.
     * 
     * @param nums      - data
     * @param currStart - starting index of current level
     * @param currEnd   - ending index of current level
     * @param step      - jump count
     * @return the minimum number of jumps to reach the end
     */
    private int bfs(int[] nums, int currStart, int currEnd, int step) {
        int nextEnd = currEnd;
        for (int i = currStart; i <= currEnd; ++i) {
            nextEnd = Math.max(nextEnd, i + nums[i]);
            if (nextEnd >= nums.length - 1) {
                return step + 1;
            }
        }
        // Optional: unable to reach the end
        if (nextEnd == currEnd) {
            return -1;
        }
        return bfs(nums, currEnd + 1, nextEnd, step + 1);
    }
}
