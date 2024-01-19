/**
 * @author: orc-dev
 * @update: Jan.19 2024
 * 
 * @leetcode: 1655. Distribute Repeating Integers
 * @tag: dp, bitmask, backtracking
 * 
 * @log
 *   - Enumarate (bit-encoded) element in a power set.
 *   - Add a max check to handle edge cases.
 * 
 * Performance
 *   - Runtime: (25 ms)
 *   - Memory: O(2^m)
 */
class Solution {
    // Pointer used to build power set
    private int p = 0;

    public boolean canDistribute(int[] nums, int[] quantity) {
        final int n = 1 << quantity.length;
        final int[] resource = countFrequency(nums);
        // Case: max demand > max resource
        if (!checkMax(resource, quantity))
            return false;
        
        final int[] demandPS = buildPowerSet(quantity, 0, new int[n], 0);
        final boolean[] local = new boolean[n];
        final boolean[] dp = new boolean[n];
        dp[0] = true;

        for (int rsc : resource) {
            // Build local state
            int k = 0;
            for (int dmd : demandPS) {
                local[k++] = (rsc >= dmd);
            }
            // Build 'dp.to_curr' from 'local' and 'dp.to_prev'
            for (int i = n - 1; i > 0; --i) {
                // for each state, enumerate all its subset as key
                for (int s = i; s > 0; s = (s - 1) & i) {
                    if (local[s] && dp[i - s]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            // Terminal.succsess check
            if (dp[n - 1])
                return true;
        }
        return false;
    }

    /** Returns true if max resource is no less than max demand. */
    private boolean checkMax(int[] resource, int[] quantity) {
        int maxR = 0;
        int maxD = 0;
        for (int x : resource) maxR = Math.max(maxR, x);
        for (int x : quantity) maxD = Math.max(maxD, x);
        return (maxR >= maxD);
    }

    /** Builds the power set of demand, encoding subset state with bitmap. */
    private int[] buildPowerSet(int[] demand, int k, int[] powerSet, int val) {
        if (k == demand.length) {
            powerSet[p++] = val;
            return null;
        }
        buildPowerSet(demand, k + 1, powerSet, val);
        buildPowerSet(demand, k + 1, powerSet, val + demand[k]);
        return powerSet;
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