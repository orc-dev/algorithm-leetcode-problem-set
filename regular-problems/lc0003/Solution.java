/**
 * @author: orc-dev
 * @update: Jan.7 2024
 * 
 * @leetcode: 3.Longest Substring Without Repeating Characters
 * @tag: sliding window, hash table
 * 
 * Tag:
 *   - Sliding window: two-pointers
 *   - Hash Table: Array-based implementation (instead of HashMap)
 * 
 * Detail:
 *   - Without init 'map' array with default value -1, instead, we 
 *     conceptually map each 0-based index to 1-based index.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        final char[] seq = s.toCharArray();
        final int[] map = new int[128];  // Hash Table
        int max = 0;

        // Sliding Window
        int l = 0;
        for (int r = 0; r < seq.length; ++r) {
            if (map[seq[r]] > 0) {
                final int m = map[seq[r]];
                for (; l < m; ++l) {
                    map[seq[l]] = 0;
                }
            }
            map[seq[r]] = r + 1;
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}
