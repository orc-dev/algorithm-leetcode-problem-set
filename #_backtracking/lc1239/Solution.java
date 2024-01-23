import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: orc-dev
 * @update: Jan.22 2024
 * 
 * @leetcode: 1239. Maximum Length of a Concatenated String with Unique Characters
 * @tag: backtracking (power set), bit manipulation
 * 
 * @log
 *   - check and encode valid string to a binary integer
 *   - build while check each subset of valid set
 *   - update the max
 * 
 * Performance
 *   - Runtime: (4 ms)
 *   - Memory: (skip) O(n)
 */
class Solution {
    private Map<Integer, Integer> map;
    private List<Integer> nums;
    private int max;

    public int maxLength(List<String> arr) {
        map = new HashMap<>();
        for (String s : arr) {
            final int x = encode(s.toCharArray());
            if (x > -1) {
                map.put(x, s.length());
            }
        }
        nums = new ArrayList<>(map.keySet());
        max = 0;
        powerSet(0, 0, 0);
        return max;
    }

    /**
     * Backtracking to build an instance of setset of the valid list, 
     * while check validity of 'string concatenation'.
     * 
     * @param k index of the current element in valid list.
     * @param subset an integer representing subset state.
     * @param count the total length of selected strings.
     */
    private void powerSet(int k, int subset, int count) {
        if (k == nums.size()) {
            max = Math.max(max, count);
            return;
        }
        // Case: not include the current element
        powerSet(k + 1, subset, count);

        // Case: check validity and include current element
        final int curr = nums.get(k);
        if ((curr & subset) == 0) {
            powerSet(k + 1, (subset | curr), count + map.get(curr));
        }
    }

    /**
     * @param seq input char array
     * @return An encoded integer if the input sequence has no 
     *         duplicate characters; otherwise, returns -1.
     */
    private int encode(char[] seq) {
        int num = 0;
        for (char c : seq) {
            int bit = 1 << (c - 'a');
            if ((num & bit) != 0) {
                return -1;
            }
            num |= bit;
        }
        return num;
    }
}