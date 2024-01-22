import java.util.ArrayList;
import java.util.List;

/**
 * @author: orc-dev
 * @update: Jan.21 2024
 * 
 * @leetcode: 247. Strobogrammatic Number II
 * @tag: recursion, backtracking
 */
class Solution {
    // Element chars of the strobogrammatic numbers
    private static char[] NUMS = {'0', '1', '8', '6', '9'};

    public List<String> findStrobogrammatic(int n) {
        return buildNumber(0, n - 1, new char[n], new ArrayList<>());
    }

    /**
     * Backtracking to construct each strobogrammatic number string from the 
     * outermost part towards the center. 
     *  - '0' cannot be placed at leading positions.
     *  - '6' and '9' cannot be placed at the center position.
     * 
     * @param i left inserting position
     * @param j right inserting position
     * @param seq intermediate array helping to build each string
     * @param ans result list
     * @return a list of all the strobogrammatic numbers that are of length n.
     */
    private List<String> buildNumber(int i, int j, char[] seq, List<String> ans) {
        if (i > j) {
            ans.add(new String(seq));
            return null;
        }
        final int start = (i == 0 && j != 0) ? 1 : 0;
        final int end   = (i == j)           ? 3 : 5;

        for (int k = start; k < end; ++k) {
            seq[i] = NUMS[k];
            seq[j] = getRotatedChar(k);
            buildNumber(i + 1, j - 1, seq, ans);
        }
        return ans;
    }

    /** Returns the rotated char of given char at index k. */
    private char getRotatedChar(int k) {
        return (k <= 2) ? NUMS[k] : NUMS[7 - k];
    }
}