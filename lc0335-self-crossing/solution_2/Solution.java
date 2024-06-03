/**
 * @author: orc-dev
 * @update: Jun.01 2024
 * 
 * @leetcode: 335. Self Crossing
 * @tag: math, geometry
 * 
 * @update:
 *   - Convert `hitSelf()` to `comp()`
 * 
 * Performance
 *   - Runtime: (1 ms), we only do once 'outer-wall-htting' check
 *   - Memory: constant
 */
class Solution {
    public boolean isSelfCrossing(int[] dist) {
        // increasing stage
        int p = 2;
        while (p < dist.length && dist[p] > dist[p - 2]) {
            p++;
        }
        // check point
        p++;
        if (comp(dist, p) && comp(dist, p - 1)) {
            return true;
        }
        // decreasing stage
        while (p < dist.length && dist[p] < dist[p - 2]) {
            p++;
        }
        return p < dist.length;
    }

    /** Compare [p] and [p - 2] - [p - 4] */
    private boolean comp(int[] dist, int p) {
        if (p >= dist.length) {
            return false;
        }
        final int p4 = (p >= 4) ? dist[p - 4] : 0;
        return dist[p] >= dist[p - 2] - p4;
    }
}