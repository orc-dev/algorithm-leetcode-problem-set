/**
 * @author: orc-dev
 * @update: Jan.22 2024
 * 
 * @leetcode: 158. Read N Characters Given read4 II - Call Multiple Times
 * @tag: sim, design
 * 
 * @log
 *   File - read4() -> Temp_Buf -> read() -> Dst_Buf
 * 
 * Performance
 *   - Runtime: (0 ms)
 *   - Memory: (skip)
 */
public class Solution extends Reader4 {
    // intermediate buffer for read4()
    private char[] temp = new char[4];
    private int s, p;  // starting and ending (exclusive) pointers

    /** Constructor resets the pointers of temp buffer */
    public Solution() {
        this.s = 0;
        this.p = 0;
    }

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int i = 0;
        while (true) {
            // Move data from temp buffer to dst buffer
            while (this.s < this.p && i < n) {
                buf[i++] = this.temp[this.s++];
            }
            if (i == n)
                break;  // Read enough chars
            
            // Fetch data from file to temp buffer
            this.p = read4(this.temp);
            this.s = 0;
            if (p == 0)
                break;  // EOF
        }
        return i;
    }
}