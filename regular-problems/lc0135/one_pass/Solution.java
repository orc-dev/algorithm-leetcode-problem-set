/**
 * @author: orc-dev
 * @update: Jun.05 2024
 * 
 * @leetcode: 135. Candy
 * @tag: 1d-inc/dec
 * 
 * @log
 * How to separate and organize the condition branches to a neat style?
 * 
 * Performance
 *   - Runtime: (2 ms), O(n)
 *   - Memory: O(1)
 */
class Solution {
    public int candy(int[] ratings) {
        int sum = ratings.length;
        int inc = 0;
        int dec = 0;
        
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i - 1] < ratings[i]) {
                inc++;
                sum += inc;
            }
            else if (ratings[i - 1] == ratings[i]) {
                inc = 0;
            }
            else {
                sum += dec;
                dec++;
                // check next to settle this decreasing interval
                if (i + 1 == ratings.length || ratings[i] <= ratings[i + 1]) {
                    sum += Math.max(0, dec - inc);
                    dec = 0;
                    inc = 0;
                }
            }
        }
        return sum;
    }
}