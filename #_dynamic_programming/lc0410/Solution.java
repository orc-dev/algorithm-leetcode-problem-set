/**
 * @author: orc-dev
 * @update: Jun.10 2024
 * 
 * @leetcode: 410. Split Array Largest Sum
 * @tag: dp, binary search
 * 
 * @log
 * Use linear time to check if the guessed number is feasible. Adjust the 
 * next guess using binary search to find the number in O(log_sum) time.
 * 
 * Performance
 *   - Runtime: O(n * log S) (0 ms)
 *   - Memory: O(1)
 */
class Solution {
    public int splitArray(int[] nums, int k) {
        // Setup bounds for the predicted value
        int lo = 0;
        int hi = 0;
        for (int x : nums) {
            lo = Math.max(lo, x);
            hi += x;
        }
        // Binary search to find minimized max-sum
        while (lo < hi) {
            final int mid = (lo + hi) / 2;
            if (canSplit(nums, k, mid)) {
                hi = mid;
            }
            else if (lo + 1 == hi) {
                break;
            }
            else {
                lo = mid;
            }
        }
        return hi;
    }

    /** Check if the array can be split within the constraint. */
    private boolean canSplit(int[] nums, int k, int predSum) {
        int count = 1;
        int currSum = 0;
        for (int x : nums) {
            if (currSum + x <= predSum) {
                currSum += x;
            }
            else if (count == k) {
                return false;
            }
            else {
                count++;
                currSum = x;
            }
        }
        return true;
    }
}