import java.util.HashMap;
import java.util.HashSet;
/**
 * @author: orc-dev
 * @update: Jun.06 2024
 * 
 * @leetcode: 403. Frog Jump
 * @tag: dp, bfs, dfs
 * 
 * @log
 *   stone(key) 0  1  2  3  5  7  8  9
 *                [ ][ ][ ][ ][ ][ ][ ]
 *                [ ][ ][ ][ ][ ][ ][ ]
 *                [ ][ ][ ][ ][ ][ ][ ]
 * 
 *   The topology of this problem resembles a 2D table or graph. Each stone s
 *   has a list of 'prevJump' to it. Each (prevJump, currStone) pair generates 
 *   outgoing edges to subsequent stones.
 * 
 * Performance
 *   - Runtime: O(m * n) (9 ms)
 *   - Memory: O(m * n)
 */
class Solution {
    // Keep track visiting info
    final private HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    
    public boolean canCross(int[] stones) {
        for (int x : stones) {
            map.put(x, new HashSet<>());
        }
        return dfs(1, 1, stones[stones.length - 1]);
    }

    /**
     * Implementation: DFS + memoization (visiting info)
     * 
     * @param prevJump size of previous jump
     * @param key the current stone value
     * @param end the target stone value
     * @return True if the frog can jump to the end
     */
    private boolean dfs(int prevJump, int key, int end) {
        final HashSet<Integer> visited = map.get(key);
        // No such key, or the given jump-key has been visited
        if (visited == null || visited.contains(prevJump)) {
            return false;
        }
        if (key == end) {
            return true;
        }
        // Lower bound must be 1 or more
        final int bound = Math.max(prevJump - 1, 1);

        // Prefer choosing longer jump distances
        for (int nextJump = prevJump + 1; nextJump >= bound; --nextJump) {
            if (dfs(nextJump, key + nextJump, end))
                return true;
        }
        // Marked as visited
        visited.add(prevJump);
        return false;
    }
}