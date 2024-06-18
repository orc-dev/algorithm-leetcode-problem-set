import java.util.ArrayList;
import java.util.List;
/**
 * @author: orc-dev
 * @update: Jun.17 2024
 * 
 * @leetcode: 241. Different Ways to Add Parentheses
 * @tag: dp (Catalan)
 * 
 * Performance
 *   - Runtime: O(n^3 * 4^n) (1 ms)
 *   - Memory: O(n^2)
 */
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        final int[] num = new int[16];
        final int[] opr = new int[16];
        final int n = extract(expression, num, opr);
        
        @SuppressWarnings("unchecked")
        final List<Integer>[][] dp = new List[n][n];

        for (int r = n - 1; r >= 0; --r) {
            for (int c = r; c < n; ++c) {
                dp[r][c] = buildList(dp, r, c, num, opr);
            }
        }
        return dp[0][n - 1];
    }

    /** Helper function builds a list for a given dp cell */
    private List<Integer> buildList(List<Integer>[][] dp, 
        int r, int c, int[] num, int[] opr
    ) {
        final List<Integer> list = new ArrayList<>();
        // diagonal grids
        if (r == c) {
            list.add(num[r]);
            return list;
        }
        // upper triangular grids
        for (int k = 0; k < (c - r); ++k) {
            final int op = opr[r + k];
            for (int a : dp[r][r + k])
                for (int b : dp[r + k + 1][c])
                    list.add(compute(op, a, b));
        }
        return list;
    }

    /** Compute the result of (a @ b) with given operator */
    private int compute(int op, int a, int b) {
        return switch(op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> 
                throw new IllegalArgumentException("error operator: " + op);
        };
    }

    /** Extract operands and operators from expression */
    private int extract(String expression, int[] num, int[] opr) {
        final char[] seq = expression.toCharArray();
        int i = 0;
        for (char c : seq) {
            if (c >= '0') {
                num[i] = num[i] * 10 + (c - '0');
            } else {
                opr[i] = c;
                i++;
            }
        }
        return i + 1;
    }
}
