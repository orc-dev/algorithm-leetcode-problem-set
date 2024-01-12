/**
 * @author: orc-dev
 * @update: Jan.11 2024
 * 
 * @leetcode: 1026.Maximum Difference Between Node and Ancestor
 * @tag: binary_tree, post_traversal
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    private int maxDiff = 0;
    
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val);
        return maxDiff;
    }

    /**
     * Post-order traversal to get lower and upper bounds from left subtree 
     * and right subtree. Then update the 'maxDiff' and returns a new bound
     * array to parent node.
     * 
     * @param node current TreeNode
     * @param pval value of parent node
     * @return an int array with { min, max } bounds on the subtree
     */
    private int[] dfs(TreeNode node, int pval) {
        if (node == null) {
            return new int[]{pval, pval};
        }
        // get and merge results from child nodes
        final int[] bound = merge(
            dfs(node.left, node.val),
            dfs(node.right, node.val)
        );
        // Update max diff
        maxDiff = max(
            maxDiff,
            Math.abs(node.val - bound[0]),
            Math.abs(node.val - bound[1])
        );
        // Returns new bounds with more extreme values
        return new int[]{
            min(node.val, bound[0]),
            max(node.val, bound[1])
        };
    }

    /** Merges two bound Arrays */
    private int[] merge(int[] a, int[] b) {
        return new int[] { 
            min(a[0], b[0]),
            max(a[1], b[1])
        };
    }

    /** Returns the MIN value among input integer list */
    private int min(int... nums) {
        int y = Integer.MAX_VALUE;
        for (int x : nums) {
            y = Math.min(x, y);
        }
        return y;
    }

    /** Returns the MAX value among input integer list */
    private int max(int... nums) {
        int y = Integer.MIN_VALUE;
        for (int x : nums) {
            y = Math.max(x, y);
        }
        return y;
    }
}

/** Leetcode TreeNode class */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}