/**
 * @author: orc-dev
 * @update: Jan.13 2024
 * 
 * @leetcode: 124. Binary Tree Maximum Path Sum
 * @tag: dfs (post-order traversal)
 * 
 */
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // Post traversal to get messages from child nodes
        final int L = dfs(node.left);
        final int R = dfs(node.right);
        final int maxChild = Math.max(0, Math.max(L, R));
        final int maxLocal = node.val + maxChild;

        // Update global max and return local-transmittable max
        updateMax(maxLocal, L + R + node.val);
        return maxLocal;
    }
    
    private void updateMax(int... nums) {
        for (int x : nums)
            max = Math.max(max, x);
    }
}
