/**
 * @author: orc-dev
 * @update: Jan.21 2024
 * 
 * @leetcode: 201. Bitwise AND of Numbers Range
 * @tag: bit manipulation
 * 
 * @log
 *   - Find common prefix of Left and Right.
 */
class Solution {
    public int rangeBitwiseAnd(int L, int R) {
        int mask = 1;

        while (L != R) {
            L &= ~mask;
            R &= ~mask;
            mask <<= 1;
        }
        return L;
    }
}