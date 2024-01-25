import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: orc-dev
 * @update: Jan.25 2024
 * 
 * @leetcode: 90. Subsets II
 * @tag: backtracking
 * @see: 78/ Subsets
 * 
 * @log
 *   Use the 'subset tree' approach to efficiently handle duplicate cases.
 *   The 'competed binary tree' approach is not very easy to handle.
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        return new SetUtils(nums).powerSet;
    }
}

/** Lightweight class that returns the powerset of a given Array. */
class SetUtils {
    final public List<List<Integer>> powerSet;
    final private List<Integer> subset;

    /** Construcutor */
    public SetUtils(int[] set) {
        Arrays.sort(set);
        this.powerSet = new ArrayList<>();
        this.subset = new ArrayList<>();
        build(set, 0);
    }

    /** Backtracking to build the power set */
    private void build(int[] set, int k) {
        this.powerSet.add(new ArrayList<>(this.subset));
        int prev = Integer.MIN_VALUE;
        
        for (int i = k; i < set.length; ++i) {
            // This condition avoids duplicated branches
            if (set[i] == prev) continue;

            prev = set[i];
            this.subset.add(prev);
            this.build(set, i + 1);
            this.subset.remove(this.subset.size() - 1);
        }
    }
}
