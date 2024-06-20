/**
 * @author: orc-dev
 * @update: Jan.11 2024 | Jun.19 2024
 * 
 * @leetcode: 84.Largest Rectangle in Histogram
 * @tag: monotonic stack
 * 
 * Performance
 *   - Runtime: O(n) (6 ms)
 *   - Memory: O(n)
 */
class Solution {
    public int largestRectangleArea(int[] bar) {
        // Create and initialize monotonic stack
        final int n = bar.length;
        final int[] stack = new int[n + 1];
        stack[0] = -1;
        int p = 0;

        int max = 0;
        for (int i = 0; i <= n; ++i) {
            // pop while computing the area
            while (p > 0 && (i == n || bar[i] < bar[stack[p]])) {
                final int h = bar[stack[p]];
                final int b = i - stack[p - 1] - 1;
                max = Math.max(max, h * b);
                p--;
            }
            // push
            p++;
            stack[p] = i;
        }
        return max;
    }
}