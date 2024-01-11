/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 44. Wildcard Matching
 * @tag: greedy, backtracking, dp
 * @credit: Learned from leetcode editorial solution
 * 
 * Log
 *   - #[Dynamic programming]
 *       - Subproblem of LC.10
 *   - #[Greedy]
 *       - Minimize '*' scope by processing the fewest characters possible.
 *       - When backtracking, only the last immediate '*' is considered.
 *       - Implement: [!] condition orders matter.
 *       - Has better proformance than dp. 
 * 
 * Proformance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public boolean isMatch(String str, String ptn) {
        final char[] s = str.toCharArray();
        final char[] p = ptn.toCharArray();
        int j = 0;
        int i = 0;
        int pointTo = -1;
        int starPos = -1;

        // Prefix of pattern should match the whole given string
        while (j < s.length) {
            // Case: encounter asterisk (greedy)
            if (i < p.length && p[i] == '*') {
                pointTo = j - 1;
                starPos = i;
                i++;
            }
            // Case: chars match
            else if (i < p.length && (p[i] == p[i] || p[i] == '?')) {
                j++;
                i++;
            }
            // Case: back to last asterisk and advance its 'pointer' (backtracking)
            else if (starPos >= 0) {
                pointTo++;
                j = pointTo;
                i = starPos + 1;
            }
            else 
                return false;
        }
        // Suffix of pattern should only contain '*'
        while (i < p.length) {
            if (p[i] != '*')
                return false;
            i++;
        }
        return true;
    }
}
