/**
 * @author: orc-dev
 * @update: Jan.19 2024
 * 
 * @leetcode: 907. Sum of Subarray Minimums
 * @tag: monotonic stack
 * @see: 84. Largest Rectangle in Histogram
 * 
 * @log
 * Example: [3,1,2,4,1,8,9,3,2]
 *      Raw stack state      psum     compressed stack (stack, count, psum)
 *    - 3                       3      (3,1,3)
 *    - 1,1                     2      (1,2,2)
 *    - 1,1,2                   4      (1,2,2) (2,1,4)
 *    - 1,1,2,4                 8      (1,2,2) (2,1,4) (4,1,8)
 *    - 1,1,1,1,1               5      (1,5,5)
 *    - 1,1,1,1,1,8            13      (1,5,5) (8,1,13)
 *    - 1,1,1,1,1,8,9          22      (1,5,5) (8,1,13) (9,1,22)
 *    - 1,1,1,1,1,3,3,3        14      (1,5,5) (3,3,14)
 *    - 1,1,1,1,1,2,2,2,2      13      (1,5,5) (2,4,13)
 *
 * Performance
 *   - Runtime: (6 ms) O(n)
 *   - Memory: (skip) O(n)
 */
class Solution {
    public int sumSubarrayMins(int[] arr) {
        // Create monotonic stack
        final Tuple[] mono = new Tuple[arr.length + 1];
        mono[0] = new Tuple(0, 0, 0);
        int p = 1;
        int sum = 0;

        for (int i = 0; i < arr.length; ++i) {
            final int key = arr[i];
            int count = 1;
            // pop
            while (key <= mono[p - 1].key) {
                count += mono[p - 1].count;
                p--;
            }
            // push
            final int psum = (count * key) + mono[p - 1].psum;
            mono[p] = new Tuple(key, count, psum);
            p++;
            // update total sum
            sum = (sum + psum) % 1000000007;
        }
        return sum;
    }
}

/** A lightweight class that organizes entries in a monotonic stack. */
class Tuple {
    int key, count, psum;
    public Tuple(int key, int count, int psum) {
        this.key = key;
        this.count = count;
        this.psum = psum;
    }
}