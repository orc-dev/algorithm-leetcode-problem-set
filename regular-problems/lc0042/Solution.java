/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @leetcode: 42.Trapping Rain Water
 * @tag: two pointers
 * 
 * Log
 *   - double pointers
 *   - traversal from end to center
 *   - move pointer from shorter-wall-side to taller-wall-side
 * 
 * Performance
 *   - Runtime: ()
 *   - Memory: ()
 */
class Solution {
    public int trap(int[] ht) {
        int water = 0;
        int l = 0;              // left pointer
        int r = ht.length - 1;  // right pointer
        int L = ht[l];          // tallest wall on LSH
        int R = ht[r];          // tallest wall on RSH

        while (l + 1 < r) {
            if (L <= R) {
                l++;
                L = Math.max(L, ht[l]);
                water += Math.max(0, L - ht[l]);
            } 
            else {
                r--;
                R = Math.max(R, ht[r]);
                water += Math.max(0, R - ht[r]);
            }
        }
        return water;
    }
}
