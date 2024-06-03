/**
 * @author: orc-dev
 * @update: Jun.02 2024
 * 
 * @leetcode: 5. Longest Palindromic Substring
 * @tag: dynamic programming (special case)
 * 
 * @log
 * This is the recursion version of the enhanced center expansion algorithm,
 * which is a little faster than the iterative version.
 * 
 * Another feature is this implementation only use two variables to keep
 * track of the optimals rather than using an DP array to do that.
 * 
 * Performance
 *   - Runtime: O(n), (1 ms)
 *   - Memory: O(n)
 */
class Solution {
    public String longestPalindrome(String s) {
        return search(s.toCharArray(), 0, 0, 0);
    }

    private String search(char[] seq, int i, int start, int size) {
        // terminal check
        if (i == seq.length) {
            return new String(seq, start + 1, size - 1);
        }
        // repeated center
        int lo = i - 1;
        while (i + 1 < seq.length && seq[i] == seq[i + 1]) {
            i++;
        }
        // bounds expansion
        int hi = i + 1;
        while (lo >= 0 && hi < seq.length && seq[lo] == seq[hi]) {
            lo--;
            hi++;
        }
        // global optimal update
        if (hi - lo > size) {
            start = lo;
            size  = hi - lo;
        }
        // recursion call
        return search(seq, i + 1, start, size);
    }
}