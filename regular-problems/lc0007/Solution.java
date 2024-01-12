/**
 * @author: orc-dev
 * @update: Jan.9 2024
 * 
 * @leetcode: 7.Reverse Integer
 * @tag: math
 * 
 * Log:
 *   - Need to handle overflow when "reversing" a valid int32 number.
 */
class Solution {
    private static final int LIM = Integer.MAX_VALUE / 10;

    public int reverse(int x) {
        // This value will case overflow after 'reversing'.
        // Meanwhile, the abs of this value is also overflow so that
        // we can not handle abs(x) in our reverse() function.
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.signum(x) * reverse(Math.abs(x), 0, LIM);
    }

    private int reverse(int src, int tgt, int lim) {
        // Base case: we are done
        if (src == 0) {
            return tgt;
        }
        // Invalid case: overflow
        if (tgt > lim) {
            return 0;
        }
        // Process next decimal
        return reverse((src / 10), (tgt * 10) + (src % 10), lim);
    }
}
