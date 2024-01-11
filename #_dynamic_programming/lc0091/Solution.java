/**
 * @author: orc-dev
 * @update: Jan.11 2024
 * 
 * @leetcode: 91.Decode Ways
 * @tag: dp (mealy, linear-tail)
 * 
 * dp.recursion
 *   - dp[i] = dp[i - 1] * 'if current single-char is decodeable' 
 *           + dp[i - 2] * 'if current double-char is decodeable'
 * 
 * Performance
 *   - Runtime: (0 ms)
 *   - Memory: (skip)
 */
class Solution {
    public int numDecodings(String s) {
        return dp(s.toCharArray(), 0, '0', 0, 1);
    }

    /**
     * Forward recursive dp function to ease the value-copy-chain operations.
     * 
     * @param seq char array of encoded message
     * @param i current index
     * @param p previous char
     * @param s2 value in state 2 steps back
     * @param s1 value in state 1 step back
     * @return the number of ways to decode the given string.
     */
    private int dp(char[] seq, int i, char p, int s2, int s1) {
        if (s1 == 0 || i == seq.length) {
            return s1;
        }
        final char c = seq[i];
        // dp.recursion
        final int s0 = valid(p, c) * s2 + valid(c) * s1;
        return dp(seq, i + 1, c, s1, s0);
    }

    /** Returns 1 if current char is decodable, otherwise 0. */
    private int valid(char c) {
        return (c == '0') ? 0 : 1;
    }

    /** Returns 1 if seq[i - 1: i] is decodable, otherwise 0. */
    private int valid(char p, char c) {
        final int num = (p - '0') * 10 + (c - '0');
        return (num >= 10 && num <= 26) ? 1 : 0;
    }
}
