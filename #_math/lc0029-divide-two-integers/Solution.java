/**
 * @author: orc-dev
 * @update: Jan.14 2024
 * 
 * @leetcode: 29. Divide Two Integers
 * @tag: math, bit manipulation
 */
class Solution {
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        final int signsXor = (a >>> 31) ^ (b >>> 31);
        final Result out = negDiv(neg(a), neg(b), 1);

        return (signsXor == 0) ? out.quo : -out.quo;
    }

    /** Division of two negative numbers */
    private Result negDiv(int a, int b, int k) {
        if (a > b) {
            return new Result(0, 0);
        }
        if (aGreaterThan2b(a, b)) {
            return new Result(a - b, k);
        }
        Result out = negDiv(a, b << 1, k << 1);
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

    /** Safely checks if a is greater than 2b, avoiding overflow for 2b */
    private boolean aGreaterThan2b(int a, int b) {
        return (a >> 1) >  b || 
               (a >> 1) == b && (a & 1) == 1;
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