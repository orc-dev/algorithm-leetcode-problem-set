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
 * This approach is very intuitive, we check each elements in the source
 * list, and build two branches (picking it and not picking it), then we 
 * check next.
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();
        constructPowerSet(nums, 0, new ArrayList<>(), powerSet);
        return powerSet;
    }

    private void constructPowerSet(int[] nums, int k, 
        List<Integer> subset, List<List<Integer>> powerSet) {
        
        if (k == nums.length) {
            powerSet.add(new ArrayList<>(subset));
            return;
        }
        // not picking current element
        constructPowerSet(nums, k + 1, subset, powerSet);

        // pick current element
        subset.add(nums[k]);
        constructPowerSet(nums, k + 1, subset, powerSet);
        subset.remove(subset.size() - 1);
    }
}