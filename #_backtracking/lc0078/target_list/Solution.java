import java.util.ArrayList;
import java.util.List;

/**
 * @author: orc-dev
 * @update: Jan.25 2024
 * 
 * @leetcode: 78. Subsets
 * @tag: backtracking
 * 
 * @log
 * This approach handles all possible cases for the 'subset.size() - 1'-th
 * element in each recursion, which means there is a for loop in each 
 * recursion call.
 * 
 * Example: For an input array [1, 2, 3], the first recursive call utilizes 
 * a for loop to handle three scenarios:
 *   - Pick {1},
 *   - Not pick {1}, pick {2},
 *   - Not pick {1, 2}, pick {3}.
 * Consequently, the remaining pool for the next level shrinks. Meanwhile, at
 * the begining of each recursive call, we add current state to answer list.
 * 
 * This approach optimizes stack space by adding subset instances at each 
 * recursive node, saving space for non-selection cases. In contrast, the 
 * alternative approach adds valid subsets only at the leaf nodes.
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();
        constructPowerSet(nums, 0, new ArrayList<>(), powerSet);
        return powerSet;
    }

    private void constructPowerSet(int[] src, int sp, 
        List<Integer> subset, List<List<Integer>> powerSet
    ) {
        powerSet.add(new ArrayList<>(subset));

        for (int i = sp; i < src.length; ++i) {
            subset.add(src[i]);
            constructPowerSet(src, i + 1, subset, powerSet);
            subset.remove(subset.size() - 1);
        }
    }
}