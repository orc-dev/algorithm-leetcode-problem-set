import java.util.ArrayList;
import java.util.List;
/**
 * @author: orc-dev
 * @update: Jan.14 2024
 * 
 * @leetcode: 17. Letter Combinations of a Phone Number
 * @tag: backtracking
 */
class Solution {
    /** Phone number - letter map */
    private final static char[][] MAP = {
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'},
    };

    /** Returns all possible letter combinations with given digits */
    public List<String> letterCombinations(String digits) {
        final List<String> list = new ArrayList<>();
        if (!digits.isEmpty()) {
            final char[] seq = digits.toCharArray();
            final char[] buf = new char[seq.length];
            backtrack(seq, 0, buf, list);
        }
        return list;
    }

    /** Backtracking to list all combinations of chars */
    private void backtrack(char[] seq, int i, char[] buf, List<String> list) {
        if (i == seq.length) {
            list.add(new String(buf));
            return;
        }
        // index mapping from current char on seq to index on MAP
        final int index = seq[i] - '2';
        for (char c : MAP[index]) {
            buf[i] = c;
            backtrack(seq, i + 1, buf, list);
        }
    }
}
