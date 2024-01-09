/**
 * @author: orc-dev
 * @update: Jan.9 2024
 * 
 * Thoughts log
 *   - index mapping
 *   - Implement an iterator for zigzag-style traversal of a data structure.
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public String convert(String s, int numRows) {
        
        ZigzagIter iter = new ZigzagIter(s.length(), numRows);
        char[] zig = new char[s.length()];

        for (int i = 0; i < zig.length; ++i) {
            zig[i] = s.charAt(iter.next());
        }
        return new String(zig);
    }
}

/**
 * A class of zigzag iterator.
 */
class ZigzagIter {
    private int i = 0;       // col pointer
    private int j = 0;       // row pointer
    private int last = -1;   // last computed index (check duplicates)
    private int count = 0;   // number of valid-visited indices
    private int SIZE = 0;    // total size
    private int STEP = 1;    // col pointer step

    /**
     * Constucts a new ZigIter.
     * 
     * @param size total size
     * @param nrow number of rows in a zigzag setting
     */
    public ZigzagIter(int size, int nrow) {
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
        // if index is negative or is duplicated with last valid one
        while (index < 0 || index == this.last) {
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

    /** Compute the current index */
    private int getIndex() {
        return this.i * this.STEP + this.j;
    }

    /** Move col-pointer */
    private void moveColPtr() {
        if (this.j >= 0) {
            this.i++;
        }
        this.j = -this.j;
    }

    /** Move row-pointer */
    private void moveRowPtr() {
        this.i = 0;
        this.j = Math.abs(this.j) + 1;
    }
}