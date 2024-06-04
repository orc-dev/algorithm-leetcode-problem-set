/**
 * @author: orc-dev
 * @update: Jun.04 2024
 * 
 * @leetcode: 132. Palindrome Partitioning 2
 * @tag: dp
 * 
 * @log
 * Implementation of enhanced center expansion:
 *   1. first process all repeated chars, (trick part: local min updates)
 *   2. then process the expanded bounds
 *   3. recursion better than iteration 
 * 
 * Performance
 *   - Runtime: (0 ms), O(n?)
 *   - Memory: O(n)
 */
class Solution {
    public int minCut(String s) {
        // init dp with -1, 0, 1, 2, ...
        final int[] dp = new int[s.length() + 1];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = i - 1;
        }
        return findMin(s.toCharArray(), dp, 0);
    }

    /** A recursive version of forward traversal updates. */
    private int findMin(char[] seq, int[] dp, int i) {
        if (i == seq.length) {
            return dp[i];
        }
        // process repeated chars
        int lo = i;
        int localMin = dp[i];
        while (i < seq.length && seq[i] == seq[lo]) {
            i++;
            localMin = Math.min(localMin, dp[i - 1]);
            dp[i] = Math.min(dp[i], localMin + 1);
        }
        // process bounds
        int hi = i;
        while (lo > 0 && hi < seq.length && seq[lo - 1] == seq[hi]) {
            hi++;
            lo--;
            dp[hi] = Math.min(dp[hi], dp[lo] + 1);
        }
        // recursion on next stage
        return findMin(seq, dp, i);
    }
}