/**
 * @author: orc-dev
 * @update: Jan.14 2024
 * 
 * @leetcode: 65. Valid Number
 * @tag: string, low ac%
 * 
 * Log
 *   # Problem has been updated by removing 'whitespace' check.
 *   - Check violation only by checking if some type of char has appeared.
 *   - Then set or reset coresponding flags.
 */
class Solution {
    public boolean isNumber(String s) {
        boolean sig = false;
        boolean dot = false;
        boolean exp = false;
        boolean num = false;
       
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-') {
                if (sig || num || dot) {
                    return false;
                }
                sig = true;
            } 
            else if (c == '.') {
                if (dot || exp) {
                    return false;
                }
                dot = true;
            } 
            else if (c == 'e' || c == 'E') {
                if (exp || !num) {
                    return false;
                }
                exp = true;
                num = false;
                sig = false;
                dot = false;
            } 
            else if (c >= '0' && c <= '9') {
                num = true;
            } 
            else {
                return false;
            }
        }
        return num;
    }
}
