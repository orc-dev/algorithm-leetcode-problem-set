/**
 * @author: orc-dev
 * @update: Jan.5 2024
 * 
 * Performance
 *   - Runtime: 19 (ms)
 *   - Memory: 45 (MB)
 */
class Solution {
    private final int[] keys = new int[128];
    private final int[] nums = new int[128];
    private int[][][] dp;

    public int removeBoxes(int[] boxes) {
        final int n = compressData(boxes);
        final int m = countMaxFreq(boxes);
        
        dp = new int[n][n][m];
        return dp(0, n - 1, 0);
    }

    /**
     * 
     * @param i inclusive starting index
     * @param k inclusive ending index
     * @param r number of boxes after k that have the same id as the k-th box
     * @return max points can get from 'box[i]' to 'box[k]' with 'r' boxes 
     *         after box[k] that have the same id as box[k].
     */
    private int dp(int i, int k, int r) {
        if (i > k)
            return 0;
        
        if (dp[i][k][r] > 0) 
            return dp[i][k][r];

        final int count = nums[k] + r;
        if (i == k)
            return dp[i][k][r] = count * count;
        
        int opt = dp(i, k - 1, 0) + count * count;

        for (int j = k - 1; j >= i; --j) {
            if (keys[j] == keys[k])
                opt = Math.max(
                    opt,
                    dp(i, j, count) + dp(j + 1, k - 1, 0)
                );
        }
        return dp[i][k][r] = opt;
    }

    // Compress continuous boxes into a single data point
    private int compressData(int[] boxes) {
        int i = -1;
        int prev = 0;
        for (int curr : boxes) {
            if (curr != prev) {
                prev = curr;
                i++;
                keys[i] = curr;
            }
            nums[i]++;
        }
        return i + 1;
    }

    // Count the max frequency of boxes
    private int countMaxFreq(int[] boxes) {
        final int[] freq = new int[100];
        int max = 0;
        for (int x : boxes) {
            freq[x]++;
            max = Math.max(max, freq[x]);
        }
        return max;
    }
}