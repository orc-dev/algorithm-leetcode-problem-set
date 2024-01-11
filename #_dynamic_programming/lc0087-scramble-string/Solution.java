/**
 * @author: orc-dev
 * @update: Jan.11 2024
 * 
 * @leetcode: 87.Scramble String
 * @tag: dp (moore, high-di)
 * 
 * implementation:
 *   - Space saving
 *   - OOP: DPTable
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    private char[] S, T;
    private DPTable tb;

    public boolean isScramble(String s1, String s2) {
        S = s1.toCharArray();
        T = s2.toCharArray();
        tb = new DPTable(S.length);
        return dp(S.length, 0, 0);
    }

    /**
     * This DP uses three dimensions to specify a subproblem state:
     * <substring_length, starting_index_s1, starting_index_s2>.
     * 
     * @param length length of the substring
     * @param s starting index of String s1
     * @param t starting index of String s2
     * @return true if s1.substring(s, length) and s2.substring(t, length) 
     *         are scramble; false otherwise.
     */
    private boolean dp(int length, int s, int t) {
        // Base case: compare one char with aonther
        if (length == 1) {
            return S[s] == T[t];
        }
        // Check if result has been computed
        final byte cachedValue = tb.get(length, s, t);
        if (cachedValue >= 0) {
            return cachedValue == 1;
        }
        // Compute new instance and store result to table
        boolean found = false;
        for (int a = 1; a < length && !found; ++a) {
            final int b = length - a;
            // dp.recursion
            found = dp(a, s, t) && dp(b, s + a, t + a) ||
                    dp(a, s, t + b) && dp(b, s + a, t);
        }
        return tb.set(length, s, t, found);
    }
}

/**
 * Lightweight class wraps a byte[][] table for storing and fetching
 * the results of dp calculation.
 *   1: true
 *   0: false
 *  -1: default (state uncompted)
 */
class DPTable {
    private final byte[][] tb;
    private final int SIZE;

    /**
     * Construct a dp table, filling with default value -1.
     * @param size row numbers of the dp table
     */
    public DPTable(int size) {
        this.SIZE = size;
        this.tb = new byte[size][];

        // Allocate array for each row with only necessary capacity
        for (int i = size - 1, k = 1; i >= 0; --i, ++k) {
            tb[i] = new byte[k * k];
            java.util.Arrays.fill(tb[i], (byte)(-1));
        }
    }

    /** Set state <legnth, s, t> with given value 'val' and returns it */
    public boolean set(int length, int s, int t, boolean val) {
        final int index = s * (this.SIZE - length + 1) + t;
        this.tb[length - 1][index] = (byte) (val ? 1 : 0);
        return val;
    }

    /** Returns value of state <legnth, s, t> */
    public byte get(int length, int s, int t) {
        final int index = s * (this.SIZE - length + 1) + t;
        return this.tb[length - 1][index];
    }
}
