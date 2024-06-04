/**
 * @author: orc-dev
 * @update: Jun.01 2024
 * 
 * @leetcode: 131. Palindrome Partitioning
 * @tag: dp, backtracking
 * 
 * @log
 * Use backtracking to find all possible results. Build a palindromic
 * substring table in advance. For example, "aabaa"
 *     [ a, aa,  _,   _, aabaa]
 *     [ *,  a,  _, aba,     _]
 *     [ _,  *,  b,   _,     _]
 *     [ _,  _,  *,   a,    aa]
 *     [ _,  _,  _,   *,     a]
 * 
 * Performance
 *   - Runtime: (5 ms), O(n * 2^n)
 *   - Memory: O(n^2)
 */
import java.util.ArrayList;
import java.util.List;

class Solution {

    private final List<List<String>> result = new ArrayList<>();
    private String[][] substr;

    public List<List<String>> partition(String s) {
        buildMemo(s.toCharArray());
        dfs(0, new ArrayList<>());
        return result;
    }

    /** Backtracking to find all valid results. */
    private void dfs(int k, List<String> record) {
        if (k == substr.length) {
            result.add(new ArrayList<>(record));
            return;
        }
        
        for (int i = k; i < substr.length; ++i) {
            if (substr[k][i] == null) continue;

            record.add(substr[k][i]);
            dfs(i + 1, record);
            record.remove(record.size() - 1);
        }
    }

    /** Dynamic programming to build a memo table */
    private void buildMemo(char[] seq) {
        final int n = seq.length;
        substr = new String[n][n];
        // diagonal underline
        for (int i = 1; i < n; ++i) {
            substr[i][i - 1] = "";
        }
        // diagonal line
        for (int i = 0; i < n; ++i) {
            substr[i][i] = String.valueOf(seq[i]);
        }
        // upper triangular zone
        for (int r = n - 2; r >= 0; --r) {
            for (int c = r + 1; c < n; ++c) {
                if (substr[r + 1][c - 1] != null && seq[r] == seq[c])
                    substr[r][c] = new String(seq, r, c + 1 - r);
            }
        }
    }
}