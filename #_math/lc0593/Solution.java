/**
 * @author: orc-dev
 * @update: Jan.15 2024
 * 
 * @leetcode: 593. Valid Square
 * @tag: math, geometry
 * 
 * @main idea: check 'squared distance' between each pair of points
 */
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        final int base = squaredDistance(p1, p2);
        return (base > 0) 
            && isValid(base, squaredDistance(p1, p3))
            && isValid(base, squaredDistance(p1, p4)) 
            && isValid(base, squaredDistance(p2, p3)) 
            && isValid(base, squaredDistance(p2, p4)) 
            && isValid(base, squaredDistance(p3, p4));
    }

    /** Returns true if two values are equal or one is twice the other. */
    private boolean isValid(int d1, int d2) {
        return d1 == d2 || d1 == (d2 << 1) || d2 == (d1 << 1);
    }

    /** Get the squared distance between two points */
    private int squaredDistance(int[] p, int[] q) {
        final int dx = p[0] - q[0];
        final int dy = p[1] - q[1];
        return (dx * dx) + (dy * dy);
    }
}
