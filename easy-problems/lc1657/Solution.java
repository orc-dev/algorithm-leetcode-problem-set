/**
 * @author: orc-dev
 * @update: Jan.13 2024
 * 
 * @leetcode: 1657. Determine if Two Strings Are Close
 * @tag: hash table, counting
 */
class Solution {
    public boolean closeStrings(String word1, String word2) {
        final char[] foo = word1.toCharArray();
        final char[] bar = word2.toCharArray();

        // 1. Lengths should be equal
        if (foo.length != bar.length) {
            return false;
        }
        // Build frequency tables
        final int[] f = new int[26];
        final int[] b = new int[26];

        for (char c : foo) f[c - 'a']++;
        for (char c : bar) b[c - 'a']++;
        
        // 2. Char sets must match
        for (int i = 0; i < f.length; ++i) {
            if (f[i] == 0 && b[i] > 0 || b[i] == 0 && f[i] > 0)
                return false;
        }
        // Sort the frequency tables
        java.util.Arrays.sort(f);
        java.util.Arrays.sort(b);

        // 3. Sorted frequency tables should match
        for (int i = 0; i < f.length; ++i) {
            if (f[i] != b[i])
                return false;
        }
        return true;
    }
}
