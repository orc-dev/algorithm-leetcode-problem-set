/**
 * @author: orc-dev
 * @update: Jan.19 2024
 * 
 * @leetcode: 105. Construct Binary Tree from Preorder and Inorder
 * @tag: d&c
 * 
 * @log
 *   - A more elegant recursive implementation (Learned from solution set)
 *   - Draw the process of an example to understand the algorithm.
 * 
 * Performance:
 *   - Runtime: (0 ms)
 *   - Memory: O(n)
 */
class Solution {
    private int[] preorder, inorder;
    private int p, i;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        this.p = 0;
        this.i = 0;
        return build(Integer.MAX_VALUE);
    }

    private TreeNode build(int callBack) {
        if (i == inorder.length || inorder[i] == callBack) {
            i++;
            return null;
        }
        final int val = preorder[p++];
        return new TreeNode(val, build(val), build(callBack));
    }
}

/** TreeNode class provided by Leetcode. */
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