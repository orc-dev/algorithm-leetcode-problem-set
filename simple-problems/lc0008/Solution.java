/**
 * @author: orc-dev
 * @update: Jan.9 2024
 * 
 * Log
 *   - Handle int32 overflow
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public int myAtoi(String s) {
        // Defines bounds parameters
        final int lim = Integer.MAX_VALUE / 10;
        final Bound[] int32_bounds = {
            new Bound( 1, +(Integer.MAX_VALUE % 10), Integer.MAX_VALUE),
            new Bound(-1, -(Integer.MIN_VALUE % 10), Integer.MIN_VALUE)
        };

        // Data and pointers
        final char[] seq = s.toCharArray();
        int i = 0;
        int signPtr = 0;
        
        // Ignore leading spaces
        while (i < seq.length && seq[i] == ' ') {
            i++;
        }
        // Check sign symbol
        if (i < seq.length && seq[i] == '+') {
            i++;
        } 
        else if (i < seq.length && seq[i] == '-') {
            signPtr = 1;
            i++;
        }

        // Parse integer
        int val = 0;
        while (i < seq.length && Character.isDigit(seq[i])) {
            final int digit = (seq[i] - '0');
            // hits bound or overflow
            if (val > lim || val == lim && digit >= int32_bounds[signPtr].onesVal()) {
                return int32_bounds[signPtr].value();
            }
            // update value and pointer
            val = (val * 10) + digit;
            i++;
        }
        return int32_bounds[signPtr].sign() * val;
    }
}

/** Organize postive and negative bounds */
record Bound(int sign, int onesVal, int value) { }
