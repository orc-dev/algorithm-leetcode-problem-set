/**
 * @author: orc-dev
 * @update: Jun.05 2024
 * 
 * @leetcode: 32. Longest Valid Parentheses
 * @tag: dp, stack, two-passes
 * 
 * Performance
 *   - Runtime: (1 ms)
 *   - Memory: O(n)
 */
class Solution {
    public int longestValidParentheses(String s) {
        final char[] seq = s.toCharArray();
        final int n = seq.length;
        // init stack
        final int[] stack = new int[n + 1];
        stack[0] = -1;
        int p = 0;
        
        int max = 0;
        for (int i = 0; i < n; ++i) {
            // push
            if (seq[i] == '(') {
                p++;
                stack[p] = i;
            }
            // pop
            else if (p > 0) {
                p--;
                max = Math.max(max, i - stack[p]);
            }
            // update stack bottom
            else {
                stack[0] = i;
            }
        }
        return max;
    }
}