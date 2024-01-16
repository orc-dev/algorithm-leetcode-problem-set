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
    public boolean isSelfCrossing(int[] dist) {
        int i = 2;  // init
        // G_loop
        while (i < dist.length && dist[i] > dist[i - 2]) {
            i++;
        }
        i++;
        // terminal check
        if (i >= dist.length) {
            return false;
        }
        // check point
        if (i >= 4 && hitSelf(dist, i)) {
            return true;
        }
        // L_loop
        while (i < dist.length && dist[i] < dist[i - 2]) {
            i++;
        }
        return i < dist.length;
    }

    /** Perform 'HIT_OUT' checking */
    private boolean hitSelf(int[] dist, int i) {
        return dist[i - 2] <= dist[i - 4] + dist[i] &&
               dist[i - 3] <= dist[i - 1] + ((i < 5) ? 0 : dist[i - 5]);
    }
}
