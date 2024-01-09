/**
 * @author: orc-dev
 * @update: Jan.9 2024
 * 
 * Thoughts log
 *   - index mapping
 *   - Implement an iterator for zigzag-style traversal of a data structure.
 *    
 *    0-STEP            1-STEP            2-STEP (out of bound)
 *     [*]               [*]               [.]
 *        [*]         [*]   [*]         [.]
 *           [*]   [*]         [*]   [*]
 *              [*]               [*]
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {

    public String convert(String s, int numRows) {
        // Create array and zig-iterator
        char[] seq = s.toCharArray();
        char[] buf = new char[seq.length];
        ZigIndexIterator zig = new ZigIndexIterator(seq.length, numRows);
        
        // Append chars in zigzag order
        for (int i = 0; i < buf.length; ++i) {
            buf[i] = seq[zig.next()];
        }
        return new String(buf);
    }
}

/**
 * An iterator for virtual 2D-array traversal in a zigzag pattern on a 1D array.
 */
class ZigIndexIterator {
    
    private int i = 0;       // virtual col pointer
    private int j = 0;       // virtual row pointer
    private int last = -1;   // last computed index (check duplications)
    private int count = 0;   // number of valid-visited indices
    private int SIZE = 0;    // total size
    private int STEP = 1;    // i-pointer step

    /**
     * Constucts a new ZigIter.
     * 
     * @param size total size
     * @param nrow number of rows in a zigzag setting
     */
    public ZigIndexIterator(int size, int nrow) {
        this.SIZE = size;
        this.STEP = Math.max(2 * (nrow - 1), 1);
    }

    /** Returns true if has next. */
    public boolean hasNext() {
        return this.count < this.SIZE;
    }

    /** Returns next index */
    public int next() {
        int index = this.getIndex();
        // if index is the same as the last index
        while (index == this.last) {
            this.moveColPtr();
            index = this.getIndex();
        }
        // if index is out of bound
        if (index >= this.SIZE) {
            this.moveRowPtr();
            index = this.getIndex();
        }
        this.count++;
        return this.last = index;
    }

    private int getIndex() {
        return this.i * this.STEP + this.j;
    }

    private void moveColPtr() {
        if (this.j >= 0) {
            this.i++;
        }
        this.j = -this.j;
    }

    private void moveRowPtr() {
        this.i = 0;
        this.j = Math.abs(this.j) + 1;
    }
}