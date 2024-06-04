import java.util.ArrayList;
import java.util.List;

/**
 * @author: orc-dev
 * @update: Jan.25 2024
 * 
 * @leetcode: 22. Generate Parentheses
 * @tag: backtracking (vali-all), recursion
 * 
 * log:
 *   - Keep track of how many '(' and ')' have been added
 *   - if (L < Half) add '('
 *   - if (R < L)    add ')'
 * 
 * Performance
 *   - Runtime: (0 ms)
 *   - Memory: O(n)
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(ans, new char[n * 2], 0, 0, 0);
        return ans;
    }
    
    /**
     * Recursive function makes decisions on how can we build the 'k-th'
     * chars in a valid parentheis string.
     * 
     * @param buf char array holding state of current valid parenthesis string
     * @param l number of '(' has been added in previous
     * @param r number of ')' has been added in previous
     * @param ans list containning all valid results
     */
    private void dfs(List<String> ans, char[] buf, int n, int l, int r) {
        if (l + r == n + n) {
            ans.add(new String(buf));
            return;
        }
        if (l < n) {
            buf[l + r] = '(';
            dfs(ans, buf, n, l + 1, r);
        }
        if (l > r) {
            buf[l + r] = ')';
            dfs(ans, buf, n, l, r + 1);
        }
    }
}