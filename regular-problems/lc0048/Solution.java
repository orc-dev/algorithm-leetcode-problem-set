/**
 * @author: orc-dev
 * @update: Jan.17 2024
 * 
 * @leetcode: 48. Rotate Image
 * @tag: index mapping
 * 
 * @note:
 *    [#][#][#][#][#][#][ ]
 *    [ ][#][#][#][#][ ][ ]
 *    [ ][ ][#][#][ ][ ][ ]
 *    [ ][ ][ ][ ][ ][ ][ ]
 *    [ ][ ][ ][ ][ ][ ][ ]
 *    [ ][ ][ ][ ][ ][ ][ ]
 *    [ ][ ][ ][ ][ ][ ][ ]
 */
class Solution {

    public void rotate(int[][] img) {
        final int k = img.length - 1;
        final int half = k >> 1;

        for (int r = 0; r <= half; ++r) {
            for (int c = r; c < k - r; ++c) {
                rotate(img, r, c, k - r, k - c, img[r][c]);
            }
        }
    }

    private void rotate(int[][] img, int r, int c, int x, int y, int temp) {
        img[r][c] = img[y][r];
        img[y][r] = img[x][y];
        img[x][y] = img[c][x];
        img[c][x] = temp;
    }
}