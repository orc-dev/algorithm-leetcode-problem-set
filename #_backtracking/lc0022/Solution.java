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
        next(new char[n * 2], 0, 0, 0, ans);
        return ans;
    }
    
    /**
     * Recursive function makes decisions on how can we build the 'k-th'
     * chars in a valid parentheis string.
     * 
     * @param buf char array holding state of current valid parenthesis string
     * @param k pointer to current processing char
     * @param l number of '(' has been added in previous
     * @param r number of ')' has been added in previous
     * @param ans list containning all valid results
     */
    private void next(char[] buf, int k, int l, int r, List<String> ans) {
        if (k == buf.length) {
            ans.add(new String(buf));
            return;
        }

        if (l < (buf.length >> 1)) {
            buf[k] = '(';
            next(buf, k + 1, l + 1, r, ans);
        }
        
        if (r < l) {
            buf[k] = ')';
            next(buf, k + 1, l, r + 1, ans);
        }
    }
}