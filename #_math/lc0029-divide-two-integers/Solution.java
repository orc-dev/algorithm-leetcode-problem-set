/**
 * @author: orc-dev
 * @update: Jan.14 2024
 * 
 * @leetcode: 29. Divide Two Integers
 * @tag: math, bit manipulation
 * 
 * Example: a = -80, b = -3
 *   (-80,  -3,  1)                 return ( -2, 26)
 *   (-80,  -6,  2)                 return ( -2, 26)
 *   (-80, -12,  4)                 return ( -8, 24)
 *   (-80, -24,  8)                 return ( -8, 24)
 *   (-80, -48, 16)                 return (-32, 16)
 *   (-80, -96, 32) -> hit (a > b), return (-80,  0)
 */
class Solution {
    /** Returns outcome of integer division (a / b) */
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        final int xorSignBits = (a ^ b) >>> 31;
        final Result out = negDiv(false, neg(a), neg(b), -1);
        
        return (xorSignBits == 0) ? -out.quo : out.quo;
    }

    /** Division of two negative numbers */
    private Result negDiv(boolean overflow, int a, int b, int k) {
        if (overflow || a > b) {
            return new Result(a, 0);
        }
        Result out = negDiv(b < 0xc000_0000, a, b << 1, k << 1);
        if (out.rem <= b) {
            out.rem -= b;
            out.quo += k;
        }
        return out;
    }

    /** Force a number to be negative */
    private int neg(int num) {
        return (num < 0) ? num : -num;
    }
}

/** Lightweight class organize the outcome of integer division */
class Result {
    int rem = 0;  // remainder
    int quo = 0;  // quotient
    public Result(int rem, int quo) {
        this.rem = rem;
        this.quo = quo;
    }
}