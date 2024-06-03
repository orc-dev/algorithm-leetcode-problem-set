/**
 * @author: orc-dev
 * @update: Jun.02 2024
 * 
 * @leetcode: 5. Longest Palindromic Substring
 * @tag: dynamic programming (special case)
 * 
 * @log
 * This algorithm is inspired by Problem 132 and offers an efficient approach 
 * to checking palindromic substrings while propagating local optimal values.
 * The key idea is to start with an anchor character 'a', process all repeated 
 * characters following this anchor, and then perform center expansion. 

 * In the next step, we set the immediate subsequent character that differs 
 * from the current anchor as the new anchor character for the next step.
 * Eg. x y z (a) a a a z y x
 * 
 * This walking strategy efficiently handles cases with n-repeated characters.
 * We use backward traversal to simplify the extraction of substrings. This 
 * algorithm is simpler and faster than the Manacher's algorithm.
 * 
 * Performance
 *   - Runtime: O(n), (4 ms)
 *   - Memory: O(n)
 */
class Solution {
    public String longestPalindrome(String s) {
        final char[] seq = s.toCharArray();
        final int n = seq.length;
        // dp[i] := the longest paldromic substring starting with i-th char
        final int[] dp = new int[n];
        dp[n - 1] = 1;

        // backward traversal
        for (int i = n - 1; i >= 0; --i) {
            // process repeated center
            int hi = i + 1;
            while (i > 0 && seq[i] == seq[i - 1]) {
                i--;
                dp[i] = Math.max(dp[i], hi - i);
            }

            // process expansion
            int lo = i - 1;
            while (lo >= 0 && hi < n && seq[lo] == seq[hi]) {
                dp[lo] = Math.max(dp[lo], hi + 1 - lo);
                lo--;
                hi++;
            }
        }
        
        // find global optimal
        int maxarg = 0;
        for (int i = 1; i < n; ++i) {
            if (dp[i] > dp[maxarg])
                maxarg = i;
        }
        return s.substring(maxarg, maxarg + dp[maxarg]);
    }
}