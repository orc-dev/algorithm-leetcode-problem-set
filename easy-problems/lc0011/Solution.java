/**
 * @author: orc-dev
 * @update: Jan.13 2024
 * 
 * @leetcode: 11. Container With Most Water
 * @tag: two pointers, greedy
 */
class Solution {
    public int maxArea(int[] ht) {
        int lo = 0;
        int hi = ht.length - 1;
        int area = 0;

        while (lo < hi) {
            if (ht[lo] <= ht[hi]) {
                area = Math.max(area, ht[lo] * (hi - lo));
                lo++;
            }
            else {
                area = Math.max(area, ht[hi] * (hi - lo));
                hi--;
            }
        }
        return area;
    }
}
