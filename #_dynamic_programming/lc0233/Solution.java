/**
 * @author: orc-dev
 * @update: Jun.17 2024
 * 
 * @leetcode: 233. Number of Digit One
 * @tag: dp, math, recursion
 * 
 * @log
 * An intuitive approach is to explore solutions when n is some 'nice' number,
 * such as a power of 10, to identify any patterns.
 * 
 * We use the following bar to help us:
 * 
 *     0   100  200  300  400  500  600  700  800  900
 *     +----+----+----+----+----+----+----+----+----+----.
 *     |    |////|    |    |    |    |    |    |    |    |
 *     +----+----+----+----+----+----+----+----+----+----.
 * 
 *  - Each power of ten forms a level.
 *  - Each level contains 10 white grids and 1 black grid.
 *  - For a given number n (e.g., n = 397):
 *    1) Identify the appropriate level to contain it. (B = 100)
 *    2) Count how many white grids are fully covered: nwhite = 397 / B
 *    3) Count how many black grids are covered: clamp(0, n - B + 1, B)
 *    4) For partially covered white grids, recursively count the 1's in
 *       the oddment of this level (97) using the next level's bar.
 * 
 *  - We can use the long type to prevent overflow; however, since only one 
 *    case might cause overflow, we add a condition to check for this and 
 *    continue using int for the problem.
 * 
 * Performance
 *   - Runtime: O(log n) (0 ms)
 *   - Memory: O(1)
 */
class Solution {
    public int countDigitOne(int n) {
        return count(n, 0, 1, 0);
    }

    /**
     * Recursively count 1's in each level by using the white-black bar.
     * 
     * @param n the original number
     * @param W number of 1's in current white grid
     * @param B size of current black grid
     * @param ans answer for the question
     * @return Number of 1's from 1 to n
     */
    private int count(int n, int W, int B, int ans) {
        // Extract current trailing digits
        final int curr = (B == 1000_000_000) ? n : n % (10 * B);
        // Count "totally-covered white grid" and "1's in the black grid"
        ans += (curr / B) * W + Math.max(0, Math.min(curr - B + 1, B));
        // Recursively count 1's at next level
        return (curr == n) ? ans : count(n, 10 * W + B, 10 * B, ans);
    }
}