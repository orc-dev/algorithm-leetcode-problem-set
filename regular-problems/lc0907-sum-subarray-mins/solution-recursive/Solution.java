/**
 * @author: orc-dev
 * @update: Jan.20 2024
 * 
 * @leetcode: 907. Sum of Subarray Minimums
 * @tag: monotonic stack
 * @see: 84. Largest Rectangle in Histogram
 * 
 * Performance
 *   - Runtime: (4 ms) O(n)
 *   - Memory: (use many stack memory) O(n)
 */
class Solution {
    private int sum = 0;
    public int sumSubarrayMins(int[] num) {
        monostack(num, 0, 0, 0);
        return sum;
    }

    /**
     * Recursive function to implement the monotonic stack to 
     * keep track of state in each iteration of the input data 
     * while updating the final result 'sum'.
     * 
     * @param num input array
     * @param i index of current element
     * @param prev value 'on top of the stack'
     * @param psum previous partial sum
     * @return null if we reach the end, otherwise returns when every
     *         the current value is greater than the value 'on top of
     *         the stack'.
     */
    private int[] monostack(int[] num, int i, int prev, int psum) {
        if (i == num.length) {
            return null;
        }
        int[] dat = {i, 0};

        while (dat != null) {
            final int k = dat[0];
            // Pop
            if (num[k] <= prev) {
                return dat;
            }
            // Update partial and total sum
            dat[1] = num[k] * (k + 1 - i) + psum; 
            sum = (sum + dat[1]) % 1000000007;
            // Push
            dat = monostack(num, k + 1, num[k], dat[1]);
        }
        return null;
    }
}