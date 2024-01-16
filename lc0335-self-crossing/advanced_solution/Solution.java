/**
 * @author: orc-dev
 * @update: Jan.16 2024
 * 
 * @leetcode: 335. Self Crossing
 * @tag: math, geometry
 * 
 * @log
 * This solution is based on more general observations and we 
 * built a state diagram to describe the state of this problem.
 *     i++
 *    +----+
 *    |    |     i++                 hitSelf()
 *    +-> "G" -------> "CHECK_POINT" ---------> "HIT_OUT"
 *         |                 |
 *         |     i++         |
 *      "START" -------------+-> "L" ---------> "HIT_IN"
 *                           |    |
 *                           +----+
 *                            i++
 * 
 * init >>> G_loop ⋅ safe_L ⋅ check_point_L ⋅ L_loop
 *   - in check_point_L state, we only do "HIT_OUT" checking (only once)
 *   - in L_loop, we keep "HIT_IN" checking
 * 
 * Performance
 *   - Runtime: (1 ms), we only do once 'outer-wall-htting' check
 *   - Memory: constant
 */
class Solution {
    public boolean isSelfCrossing(int[] d) {
        int i = 2;  // init
        // G_loop
        while (i < d.length && d[i] > d[i - 2]) {
            i++;
        }
        i++;
        // terminal check
        if (i >= d.length) {
            return false;
        }
        // check point
        if (i >= 4 && hitSelf(d, i)) {
            return true;
        }
        // L_loop
        while (i < d.length && d[i] < d[i - 2]) {
            i++;
        }
        return i < d.length;
    }

    /** Perform 'HIT_OUT' checking */
    private boolean hitSelf(int[] d, int i) {
        return d[i - 2] <= d[i - 4] + d[i] &&
               d[i - 3] <= d[i - 1] + ((i < 5) ? 0 : d[i - 5]);
    }
}
