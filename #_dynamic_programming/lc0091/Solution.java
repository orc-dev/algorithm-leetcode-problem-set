/**
 * @author: orc-dev
 * @update: Jan.11 2024 | Jun.06 2024
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
 *   - Memory: O(1)
 */
class Solution {
    public int numDecodings(String s) {
        return dp(s.toCharArray(), 0, 0, 0, 1);
    }

    /**
     * Recursive version of dp.
     * 
     * @param seq encoded string
     * @param i index of current char
     * @param prev previous digit
     * @param p2 state[i - 2]
     * @param p1 state[i - 1]
     * @return the number of ways to decode the sequence[0:i]
     */
    private int dp(char[] seq, int i, int prev, int p2, int p1) {
        if (i == seq.length || p1 == 0) {
            return p1;
        }
        int p0 = 0;
        // check the single-digit number
        final int curr = seq[i] - '0';
        if (curr >= 1 && curr <= 9) {
            p0 += p1;
        }
        // check the two-digit-number
        final int tens = prev * 10 + curr;
        if (tens >= 10 && tens <= 26) {
            p0 += p2;
        }
        return dp(seq, i + 1, curr, p1, p0);
    }
}