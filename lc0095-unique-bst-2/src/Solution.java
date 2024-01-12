import java.util.ArrayList;
import java.util.List;

/**
 * @author: orc-dev
 * @update: Jan.12 2024
 * 
 * @leetcode: 95. Unique Binary Search Trees II
 * @tag: dp (moore), binary search tree
 * 
 * Thoughts
 *   - dp (Catalan sequence)
 *   - organize the Cartesian Product of 'root * leftSet * rightSet'
 * 
  * Implementation:
 *   - Deep-Copy Approach:
 *     Previous solutions did not return trees with a deep-copy style. In 
 *     those solutions, trees in the list were interleaved with each other, 
 *     resulting in a messy block of memory. The current solution returns 
 *     trees with a deep-copy fashion. Each tree is independent, with no 
 *     shared subtrees among different trees.
 * 
 *   - Space-Saving Strategies:
 *     1. Utilizing 32-bit integers to represent unique BSTs.Each 4-bit group
 *        represent a value inserting into the tree, from the least significant
 *        side to most sigificant side.
 *        Eg. The tree sequence for the below BST is 0x312     
 *               [2]      
 *              /   \
 *            [1]   [3]
 *       
 *     2. Avoiding the storage of duplicate tree sequences. Instead, we add 
 *        offsets when creating new tree sequences based on existing 
 *        instances for efficiency.
 *        Eg. Suppose we want to create a subtree with the following struct:
 *               [4]
 *              /   \
 *            [3]   [5]  
 *        We only need to use the tree sequence 0x312 plus the offset 0x222.
 * 
 * Performance
 *   - Runtime: (..) (maybe a bit slower than the shallow-copy approach)
 *   - Memory: (..) (may use less spaces)
 */
class Solution {

    @SuppressWarnings("unchecked")
    public List<TreeNode> generateTrees(int n) {
        // dp.init
        ArrayList<Integer>[] table = new ArrayList[n + 1];
        table[0] = new ArrayList<>();
        table[0].add(0);

        List<TreeNode> result = new ArrayList<>();
        List<Integer> treeSeq = treeSeq(n, table);  // dp.run

        // Create trees from sequence and add to list
        for (int seq : treeSeq) {
            result.add(buildTree(seq));
        }
        return result;
    }

    /**
     * A dynamic programming function generates a list of tree sequences 
     * representing unique Binary Search Trees (BSTs) with a size of 'n' 
     * by recursively calling itself with subproblem instances.
     * 
     * Eg.
     *               root   n 
     *                |     |
     *    1  2  3  4  5  6  7
     *    |________|     |__|
     *    (root - 1)  (n - root)
     * 
     *    - seqR need add an offset (0x55) so that (0x12) becomes to (0x67),
     *      which is the root, 0x5, repeating (n - root) times
     *    - seqR then need to left shift 5 (the root value) * 4 (since we use
     *      4 bits to represent a number), that is root * 4 = root << 2.
     *    - so, finally we have: (seqR + offset(root, n - root)) << (root << 2)
     * 
     * @param n size of BST
     * @param table dp memoisation table
     * @return a list of integers, each represents a BST with unique struct.
     */
    private List<Integer> treeSeq(int n, ArrayList<Integer>[] table) {
        if (table[n] != null) {
            return table[n];
        }
        table[n] = new ArrayList<>();
        // Recursively construct new tree sequence based on 'previous' results
        for (int root = 1; root <= n; ++root)
            for (int seqL : treeSeq(root - 1, table))
                for (int seqR : treeSeq(n - root, table))
                    // dp.recursion
                    table[n].add(
                        root 
                        | (seqL) << (4) 
                        | (seqR + offset(root, n - root)) << (root << 2)
                    );
        
        return table[n];
    }

    /** Eg. mask(0x7, 3) returns 0x777 */
    private int offset(int unit, int repeat) {
        int mask = 0;
        while (repeat > 0) {
            mask = mask << 4 | unit;
            repeat--;
        }
        return mask;
    }

    /** Build a BST from given sequence. */
    private TreeNode buildTree(int seq) {
        TreeNode t = null;
        int val;
        while ((val = seq & 0xf) != 0) {
            t = insert(t, val);
            seq >>>= 4;  // Use unsigned right shift
        }
        return t;
    }

    /** Binary search tree insertion operation. */
    private TreeNode insert(TreeNode node, int val) {
        if (node == null)
            return new TreeNode(val);

        if (val > node.val) {
            node.right = insert(node.right, val);
        } else {
            node.left = insert(node.left, val);
        }
        return node;
    }
}