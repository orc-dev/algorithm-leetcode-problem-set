import java.util.ArrayList;
import java.util.List;
/**
 * @author: orc-dev
 * @update: Jun.21 2024
 * 
 * @leetcode: 95. Unique Binary Search Trees II
 * @tag: dp (Catalan), binary search tree
 * 
 * @key point:
 *   - Time complexity: O(4^n / sqrt(n))
 *   - Bit-based implementation: 
 *       * use 4-bit to represent a number
 *       * a 8-node tree can be represent with a 32-bit integer
 *       * easy to build larger tree from shorter "branches"
 *   - [!] Use logic shift `>>>` for inserting the tree
 * 
 * Performance
 *   - Runtime: O(Catalan.n) (1 ms)
 *   - Memory: O(Catalan.sum)
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        // Utility constant for computing new tree-seq-num
        final int[] MASK = { 
            0x0, 
            0x0000_000f, 0x0000_00ff, 0x0000_0fff, 0x0000_ffff,
            0x000f_ffff, 0x00ff_ffff, 0x0fff_ffff, 0xffff_ffff
        };
        final int[] BASE = { 
            0x0, 
            0x1111_1111, 0x2222_2222, 0x3333_3333, 0x4444_4444,
            0x5555_5555, 0x6666_6666, 0x7777_7777, 0x8888_8888
        };
        // Init dp and compute size
        final int[][] dp = new int[n + 1][];
        final int[] size = catalanNums(n);
        
        // Bottem-up dp
        for (int tree = 0; tree <= n; ++tree) {
            dp[tree] = new int[size[tree]];
            int k = 0;

            for (int root = 1; root <= tree; ++root) {
                final int sizeL = root - 1;
                final int sizeR = tree - root;
                final int incR = BASE[root] & MASK[sizeR];
                final int shfR = (sizeL + 1) * 4;
   
                for (int l : dp[sizeL])
                    for (int r: dp[sizeR])
                        dp[tree][k++] = root | (l << 4) | (r + incR) << shfR;
            }
        }
        // Build BST's from encoded numbers
        final List<TreeNode> res = new ArrayList<>();
        for (int code : dp[n]) {
            res.add(buildBST(code));
        }
        return res;
    }

    /** Build a BST from given tree-seq-num `code` */
    private TreeNode buildBST(int code) {
        TreeNode root = null;
        while (code != 0) {
            root = insert(root, code & 0xf);
            code >>>= 4;
        }
        return root;
    }

    /** Insertion helper in building a BST */
    private TreeNode insert(TreeNode node, int val) {
        if (node == null)
            return new TreeNode(val);
        
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    /** Linear implementation of computing the Catalan numbers */
    private int[] catalanNums(int n) {
        final int[] c = new int[n + 1];
        c[0] = 1;
        for (int i = 1; i <= n; ++i) {
            c[i] = c[i - 1] * (4 * i - 2) / (i + 1);
        }
        return c;
    }
}