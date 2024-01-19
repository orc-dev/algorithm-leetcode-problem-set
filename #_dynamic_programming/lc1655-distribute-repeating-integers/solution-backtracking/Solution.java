import java.util.Arrays;

/**
 * @author: orc-dev
 * @update: Jan.19 2024
 * 
 * @leetcode: 1655. Distribute Repeating Integers
 * @tag: dp, bitmask, backtracking
 * 
 * @log
 *   - Traversal quantity array in non-increasing order (sort)
 *   - Skip (pruning) resource item that has the same value with previous item
 *   - Restrictions that 'resouce type no more than 50' and 'demands no more than
 *     10' make the greed-ish backtracking work
 * 
 * Performance
 *   - Runtime: (3 ms)
 *   - Memory: (skip)
 */
class Solution {

    public boolean canDistribute(int[] nums, int[] quantity) {
        int[] resource = countFrequency(nums);
        Arrays.sort(resource);
        Arrays.sort(quantity);
        return serveNext(resource, quantity, quantity.length - 1);
    }

    /** Backtracking to verify whether all demands can be fulfilled. */
    private boolean serveNext(int[] resource, int[] demand, int k) {
        if (k < 0)
            return true;

        int lastValue = 0;
        for (int i = 0; i < resource.length; ++i) {
            // Pruning
            if (resource[i] < demand[k] || resource[i] == lastValue)
                continue;

            resource[i] -= demand[k];
            if (serveNext(resource, demand, k - 1)) {
                return true;
            }
            resource[i] += demand[k];
            lastValue = resource[i];
        }
        return false;
    }

    /** Returns the frequency table of raw data */
    private int[] countFrequency(int[] raw) {
        // Count frequency
        final int[] freqTable = new int[1001];
        for (int x : raw) {
            freqTable[x]++;
        }
        // Count non-zero values
        int i = 0;
        for (int x : freqTable) {
            if (x > 0) i++;
        }
        // Extracting non-zero values
        final int[] filteredData = new int[i];
        i = 0;
        for (int x : freqTable) {
            if (x > 0) filteredData[i++] = x;
        }
        return filteredData;
    }
}