/**
 * @author: orc-dev
 * @update: Jan.11 2024
 * 
 * @leetcode: 84.Largest Rectangle in Histogram
 * @tag: monotonic stack
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public int largestRectangleArea(int[] ht) {
        // Init a monotonic stack
        final int[] stack = new int[ht.length + 1];
        stack[0] = -1;
        int p = 0;
        int area = 0;

        for (int i = 0; i <= ht.length; ++i) {
            // Pop
            while ((p > 0) && (i == ht.length || ht[i] < ht[stack[p]])) {
                p--;
                // Calcuate and update max area
                final int h = ht[stack[p + 1]];
                final int b = i - stack[p] - 1;
                area = Math.max(area, b * h);
            }
            // Push
            p++;
            stack[p] = i;
        }
        return area;
    }
}
