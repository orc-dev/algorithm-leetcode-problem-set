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

    private final int[] OFFSET = {
        0x00000000, 0x11111111, 0x22222222, 
        0x33333333, 0x44444444, 0x55555555, 
        0x66666666, 0x77777777, 0x88888888
    };

    private final int[] MASK = {
        0x0,      0xF,        0xFF,     
        0xFFF,    0xFFFF,     0xFFFFF, 
        0xFFFFFF, 0xFFFFFFF , 0xFFFFFFFF
    };

    public List<TreeNode> generateTrees(int n) {
        // Compute size
        final int[] size = new int[n + 1];
        size[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                size[i] += size[j] * size[i - j - 1];
            }
        }
        // dp.init
        int[][] table = new int[n + 1][];
        table[0] = new int[]{0};
        
        List<TreeNode> result = new ArrayList<>();
        int[] treeSeq = treeSeq(n, table, size);

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
    private int[] treeSeq(int n, int[][] table, int[] size) {
        if (table[n] != null) {
            return table[n];
        }
        table[n] = new int[size[n]];
        int i = 0;

        // Recursively construct new tree sequence based on 'previous' results
        for (int root = 1; root <= n; ++root) {
            final int offset = OFFSET[root] & MASK[n - root];

            for (int seqL : treeSeq(root - 1, table, size)) {
                for (int seqR : treeSeq(n - root, table, size)) {
                    // dp.recursion
                    table[n][i++] = root 
                                  | (seqL << 4) 
                                  | (seqR + offset) << (root * 4);
                }
            }
        }
        return table[n];
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