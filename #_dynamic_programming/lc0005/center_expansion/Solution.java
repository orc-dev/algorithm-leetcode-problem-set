/**
 * @author: orc-dev
 * @update: Jun.02 2024
 * 
 * @leetcode: 5. Longest Palindromic Substring
 * @tag: dynamic programming (special case)
 * 
 * @log
 * This implementation of Manacher's algorithm finds the longest palindromic 
 * substring in O(n) time. The key idea is to manipulate the computation in 
 * an "expanded space" rather than the "original space".
 *
 * By inserting empty chars into the original string, we transform it from
 * "abc" to "_a_b_c_", creating a new space with beneficial properties for 
 * simplifying the algorithm. Note: "_a_b_c_" is better than "a_b_c".
 *
 * The dp array has a length equal to the expanded character array. dp[i] 
 * contains the radius length (excluding the center) of the palindromic 
 * substring centered at index i, in the expanded space. This value also 
 * represents the length of the palindromic substring in the original space, 
 * starting from the index (i - dp[i]) / 2.
 * 
 * @key idea
 * center expansion in "_a_b_c_" space.
 * 
 * Performance
 *   - Runtime: O(n), (7 ms)
 *   - Memory: O(n)
 */
class Solution {
    public String longestPalindrome(String s) {
        final char[] seq = createExpandedSeq(s);
        final int n = seq.length;
        final int[] dp = new int[n];

        int currCenter = 0;  // index of the current reference center
        int rightBound = 0;  // index of associate right-side bound
        int maxarg     = 0;  // index of max value

        for (int i = 1; i + dp[maxarg] < n; ++i) {
            // take advantage of the mirror node
            if (i < rightBound) {
                final int mirror = currCenter * 2 - i;
                dp[i] = Math.min(dp[mirror], rightBound - i);
            }
            // center expansion
            int lo = i - dp[i];
            int hi = i + dp[i];
            while (lo - 1 >= 0 && hi + 1 < n && seq[lo - 1] == seq[hi + 1]) {
                dp[i]++;
                lo--;
                hi++;
            }
            // update reference center and bound
            if (hi > rightBound) {
                currCenter = i;
                rightBound = hi;
            }
            // update maxarg
            if (dp[i] > dp[maxarg]) {
                maxarg = i;
            }
        }
        final int begin = (maxarg - dp[maxarg]) / 2;
        return s.substring(begin, begin + dp[maxarg]);
    }

    /**
     * This function create a new char array from the original String
     * by inserting empty chars. Eg. "abc" -> "_a_b_c_".
     * 
     * @param s the original input string
     * @return a char array
     */
    private char[] createExpandedSeq(String s) {
        final char[] seq = new char[s.length() * 2 + 1];
        int i = 1;
        for (char c : s.toCharArray()) {
            seq[i] = c;
            i += 2;
        }
        return seq;
    }
}