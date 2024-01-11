/**
 * @author: orc-dev
 * @update: Jan.10 2024
 * 
 * @brief: 2385. Amount of time for binary tree to be infected
 * @tags: binary tree, post traversal
 * 
 * Log
 *   - Three cases: source node, regular node, ancestors of source 
 *   - Two types of messages: (int) distance, (bool) fromTheSource'
 *   - implementation: use last bit to represent 'fromTheSource'
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    private int time = 0;
    public int amountOfTime(TreeNode root, int start) {
        dfs(root, start);
        return time >> 1;
    }

    /**
     * Post traversal the tree, each node organize messages from child nodes,
     * update the 'time' variable if necessary and propagate results upwards.
     * 
     * @param node current node
     * @param start key of the source node
     * @return an integer containing messages of 'longest distance' and a 
     *         'isFromSource' bit.
     */
    private int dfs(TreeNode node, int start) {
        if (node == null) {
            return 0;
        }
        // post traversal to get messages from child nodes
        final int L = dfs(node.left, start);
        final int R = dfs(node.right, start);
        
        // Case 1: the source node
        if (node.val == start) {
            time = Math.max(time,  Math.max(L, R));
            return 3;
        }
        // Case 2: regular nodes
        if ((L & 1) + (R & 1) == 0) {
            return  Math.max(L, R) + 2;
        }
        // Case 3: ancestors of the source node
        time = Math.max(time, L + R);
        return ((L & 1) == 1) ? (L + 2) : (R + 2);
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