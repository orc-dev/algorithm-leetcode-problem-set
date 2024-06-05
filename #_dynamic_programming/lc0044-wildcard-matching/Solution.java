/**
 * @author: orc-dev
 * @update: Jan.10 2024 | Jun.04 2024
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
 *   - Runtime: (2 ms)
 *   - Memory: O(1)
 */
class Solution {
    public boolean isMatch(String s, String p) {
        final char[] text  = s.toCharArray();
        final char[] regex = p.toCharArray();

        int i = 0;    // text pointer
        int j = 0;    // regex pointer
        int y = 0;    // index for text pointer jump back
        int x = 0;    // index for regex pointer jump back

        while (i < text.length) {
            // case: point match
            if (j < regex.length && (text[i] == regex[j] || regex[j] == '?')) {
                i++;
                j++;
            }
            // case: wild char '*'
            else if (j < regex.length && regex[j] == '*') {
                y = i;
                x = j + 1;
                j++;
            }
            // case: jump back to adjust scope of the last '*'
            else if (x > 0) {
                i = y;
                j = x;
                y++;
            }
            else return false;
        }
        
        while (j < regex.length && regex[j] == '*') {
            j++;
        }
        return j == regex.length;
    }
}