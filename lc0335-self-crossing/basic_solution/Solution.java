/**
 * @author: orc-dev
 * @update: Jan.16 2024
 * 
 * @leetcode: 335. Self Crossing
 * @tag: math, geometry
 * 
 * @log
 * This solution relies on local observations to formulate checking conditions 
 * for each iteration step. It identifies that self-hitting occurs in only two 
 * cases:
 *      case.1: inner-wall hitting           case.2: outer-wall hitting
 *                  p2                               p4
 *                +----+                           +----+
 *             p1 |    | p3                        |    |p5  
 *                +----|-->                     p3 |  <-|------+
 *                  p0                             |      p0   | p1
 *                                                 +-----------+
 *                                                      p2
 * 
 * We found that keeping track of the 5 previous elements is sufficient. 
 * Additionally, checking conditions can be constructed based on difference 
 * tokens, leading to the following notation:
 *     w = p3 - p5
 *     x = p2 - p4
 *     y = p1 - p3
 *     z = p0 - p2
 * 
 * We have the following checking conditions:
 *     case.1: y <= 0 && z >= 0
 *     case.2: y <= 0 && w <= p1 && x >= 0 && x <= p0
 * 
 * Performance
 *   - Runtime: (5 ms) Have to check more conditions in each iteration steps.
 *   - Memory: Use many memory on stack due to its recursive implementation.
 */
class Solution {
    public boolean isSelfCrossing(int[] dist) {
        if (dist.length <= 3) {
            return false;
        }
        return next(dist, 0, 0, dist[0], -dist[0], dist[1], 2);
    }

    /**
     * This method is used to determine if the current segment crosses itself. 
     * To achieve this, a list of previous states needs to be tracked. Instead 
     * of storing the raw data of the previous 5 elements, this function 
     * optimizes by keeping track of the difference-tokens over that range, 
     * which reduces the workload of condition checking in each iteration step.
     * 
     * @param dist distance array (input data)
     * @param w token (p3 - p5)
     * @param x token (p2 - p4)
     * @param y token (p1 - p3)
     * @param z -p2, which is used to form token (p0 - p2)
     * @param prev previous point (p1)
     * @param i index of current point (p0)
     * @return if is self crossing from the i-th element.
     */
    private boolean next(int[] dist, int w, int x, int y, int z, int prev, int i) {
        // reach the end of list
        if (i >= dist.length)
            return false;
        
        final int curr = dist[i];
        z += curr;
        
        if (y <= 0 && z >= 0) 
            return true;  // Case: hits inner wall
        
        if (y <= 0 && w <= prev && x >= 0 && x <= curr)
            return true;  // Case: hits outer wall
        
        return next(dist, x, y, z, -prev, curr, i + 1);  // Checks next
    }
}
