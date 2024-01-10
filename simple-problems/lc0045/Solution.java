/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @tag: #[BFS]
 * 
 * Performance:
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public int jump(int[] nums) {
        // Check if we can reach the end within current level
        if (nums.length == 1) {
            return 0;
        }
        return bfs(nums, 0, 0, 0, 0);
    }

    /**
     * An recursive implementation of BFS algorithm, where we only use 'nextEnd'
     * as a checking parameter for termination.
     * 
     * @param nums - data
     * @param i - index of current visiting element
     * @param currEnd - ending index of current level
     * @param nextEnd - ending index of next level
     * @param step - number of jumps
     * @return the minimum number of jumps to reach the end
     */
    private int bfs(int[] nums, int i, int currEnd, int nextEnd, int step) {
        nextEnd = Math.max(nextEnd, i + nums[i]);
        if (nextEnd >= nums.length - 1)
            return step + 1;
        
        if (i < currEnd)
            return bfs(nums, i + 1, currEnd, nextEnd, step);
        else
            return bfs(nums, i + 1, nextEnd, nextEnd, step + 1);
    }
}
