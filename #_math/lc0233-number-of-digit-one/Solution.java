/**
 * @author: orc-dev
 * @update: Jan.21 2024
 * 
 * @leetcode: 233. Number of Digit One
 * @tag: math. dp
 * 
 * @log
 *     0  1  2  3  4  5  6  7  8  9
 *    [ ][#][ ][ ][ ][ ][ ][ ][ ][ ]
 * 
 * Performance
 *   - Runtime: (0 ms) O(logn)
 *   - Memory: (skip)
 */
class Solution {
    public int countDigitOne(int n) {
        return countOne(9, (int) 1e9, n, 0);
    }
    
    /**
     * For each position, calculate the ones.
     * 
     * @param exp expononet
     * @param div divisor (eg. 10, 100, ...)
     * @param num current number to process
     * @param cnt total count of 1's in given number
     * @return the number of digit 1's in a given number
     */
    private int countOne(int exp, int div, int num, int cnt) {
        if (exp < 0) {
            return cnt;
        }
        final int nextDiv = div / 10;
        final int quo = num / div;
        final int rem = num % div;

        cnt += quo * exp * nextDiv;   // number of entire while squares
        cnt += Math.clamp(num + 1 - div, 0, div);   // shadow nums

        return countOne(exp - 1, nextDiv, rem, cnt);
    }
}
