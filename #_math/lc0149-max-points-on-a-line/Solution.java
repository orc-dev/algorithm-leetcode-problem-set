import java.util.Collections;
import java.util.HashMap;

/**
 * @author: orc-dev
 * @update: Jan.14 2024
 * 
 * @leetcode: 149. Max Points on a Line
 * @tag: geometry, hash table
 * 
 * @solution-1 O(n^2)
 *   - point, slope -> line
 *   - for each point, compute and cache slope with all next points
 *   - Hash table
 *   - implementation: slope (integer, double or struct, etc.)
 * 
 * @solution-2 O(n^3)
 *   - point, point -> line
 *   - for each pair of points p and q, check all next pints r,
 *     if (p,r) and (q,r) on the same line, 'count++'
 */
class Solution {
    public int maxPoints(int[][] points) {
        final HashMap<Integer, Integer> slopeCnt = new HashMap<>();
        int maxPts = 0;

        for (int i = 0; i < points.length - 1; ++i) {
            slopeCnt.clear();
            for (int j = i + 1; j < points.length; ++j) {
                slopeCnt.merge(slope(points, i, j), 1, Integer::sum);
            }
            maxPts = Math.max(maxPts, Collections.max(slopeCnt.values()));
        }
        return maxPts + 1;
    }

    /** Encode the slope to an integer */
    private Integer slope(int[][] pts, int i, int j) {
        int dy = pts[j][1] - pts[i][1];
        int dx = pts[j][0] - pts[i][0];
        
        if (dx == 0) return 0;
        if (dy == 0) return 1;

        final int signBit = (dy ^ dx) & 0x8000_0000;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        final int gcd = gcd(dy, dx);

        return signBit | (dy / gcd) << 16 | (dx / gcd);
    }

    /** Returns the GCD of two integers */
    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}