import java.util.Arrays;
/**
 * @author: orc-dev
 * @update: Jan.11 2024 | Jun.05 2024
 * 
 * @leetcode: 87.Scramble String
 * @tag: dp (moore, high-di)
 * 
 * Performance
 *   - Runtime: O(n^4), (2 ms)
 *   - Memory: O(n^3), (1/3)
 */
class Solution {

    private char[] s, t;
    private Boolean[][][] dp;
    private final int[] table = new int[26];

    public boolean isScramble(String s1, String s2) {
        s = s1.toCharArray();
        t = s2.toCharArray();

        final int n = s.length;
        dp = new Boolean[n + 1][][];
        for (int i = 1, j = n; i <= n; ++i, --j) {
            dp[i] = new Boolean[j][j];
        }
        return dp(n, 0, 0);
    }

    /**
     * @param size the size of substrings
     * @param i starting index on s1
     * @param j starting index on s2
     * @return if the two substrings are scrambled
     */
    private boolean dp(int size, int i, int j) {
        if (dp[size][i][j] != null) {
            return dp[size][i][j];
        }

        if (!sameFreq(size, i, j)) {
            return dp[size][i][j] = false;
        }

        if (size == 1) {
            return dp[size][i][j] = (s[i] == t[j]);
        }

        for (int k = 1; k < size; ++k) {
            final int q = size - k;

            if (dp(k, i, j    ) && dp(q, i + k, j + k) || 
                dp(k, i, j + q) && dp(q, i + k, j    )) {
                return dp[size][i][j] = true;
            }
        }
        return dp[size][i][j] = false;
    }

    /** check if two substrings contains chars with the same frequency */
    private boolean sameFreq(int size, int i, int j) {
        Arrays.fill(table, 0);

        for (int k = i; k < i + size; ++k) {
            table[s[k] - 'a']++;
        }

        for (int k = j; k < j + size; ++k) {
            table[t[k] - 'a']--;
            if (table[t[k] - 'a'] < 0)
                return false;
        }
        return true;
    }
}