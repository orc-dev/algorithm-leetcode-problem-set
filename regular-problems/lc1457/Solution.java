/**
 * author: orc-dev
 * update: Jan.23 2024
 * 
 * @leetcode: 1457. Pseudo-Palindromic Paths in a Binary Tree
 * @tag: bit manipulation, dfs
 * 
 * @log
 *   - use a single integer to encode the state of visited nodes
 *   - a pseudo-palindromic path can have at most one value with odd frequency
 *   - use reset-1-bit-trick 'state & (state - 1)' to see if is 0
 * 
 * Performance
 *   - Runtime: (5 ms)
 *   - Memory: (skip) O(depth)
 */
class Solution {
    private int ans = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int bitmap) {
        // Get the bitmap for path from root to current node
        final int curr = bitmap ^ (1 << node.val);

        // For leave node, check bitmap and update ans
        if (node.left == null && node.right == null) {
            ans += ((curr & (curr - 1)) == 0) ? 1 : 0;
            return;
        }
        // Otherwise, continue to preorder traversal for child nodes
        if (node.left != null) {
            dfs(node.left, curr);
        }
        if (node.right != null) {
            dfs(node.right, curr);
        }
    }
}


/** Leetcode's TreeNode class */
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