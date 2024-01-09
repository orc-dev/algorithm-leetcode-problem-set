import java.util.PriorityQueue;

/**
 * @author: orc-dev
 * @update: Jan.8 2024
 * 
 * Thought log:
 *   - Different operations have varying abilities to address different types 
 *     of errors:
 *         |     | tooLong | tooShort | missType | repeating |
 *         | del |    1    |    0     |     0    |     1     |
 *         | ins |    0    |    1     |     1    |     2     |
 *         | rep |    0    |    0     |     1    |     3     |
 * 
 *   - Priority of different type of errors for resource allocation:
 *         | Priority | Primary error | Operation |    Side benefits    |
 *         |     1    |    tooLong    |    del    | repeating           |
 *         |     1    |    tooShort   |    ins    | repeating; missType |
 *         |     2    |    repeating  |    rep    |            missType |
 *         |     3    |    missType   |    rep    | N/A                 |
 * 
 * Performance
 *   - Runtime (..)
 *   - Memory (..)
 */
class Solution {
    // Data stuctures for organizing different types of error
    private int tooShortError = 0;
    private int tooLongError = 0;
    private int missTypeError = 0;
    private PriorityQueue<Integer> repeatingError;

    public int strongPasswordChecker(String password) {
        checkErrors(password.toCharArray());
        
        // Only the 'delete' operation can fix 'tooLongError'
        // Meanwhile, it can help fixed some 'repeatingError' with power 1
        final int del = tooLongError;
        optimizeSideBenefit(del, 1);

        // Only the 'insert' operation can fix 'tooShortError'
        // Meanwhile, it can help fixed some 'repeatingError' with power 2
        final int ins = tooShortError;
        optimizeSideBenefit(ins, 2);
        
        // The 'replace' operation can fix the 'repeatingError' with power 3
        int rep = repeatingError.stream().mapToInt(x -> x / 3).sum();

        // Handle 'missTypeError' if necessary
        rep += Math.max(0, missTypeError - ins - rep);
        
        return (del + ins + rep);
    }

    /**
     * When one type of resource (operation) fixing then primary type of error,
     * it can also have some side benefits which we need to optimized.
     * 
     * @param resource operation numbers
     * @param power the efficiency this operation can fix the 'repeatingError'
     */
    private void optimizeSideBenefit(int resource, int power) {
        while (resource > 0 && !repeatingError.isEmpty()) {
            final int val = repeatingError.poll() - power;
            if (val >= 3) {
                repeatingError.add(val);
            }
            resource--;
        }
    }

    /**
     * Check the password and update the status of different types of error.
     * @param pw char array containing the password.
     */
    private void checkErrors(char[] pw) {
        // initialize PriorityQueue with a modulo 3 comparator
        repeatingError = new PriorityQueue<Integer>((a, b) -> a % 3 - b % 3);

        // check length error
        this.tooShortError = Math.max(0, 6 - pw.length);
        this.tooLongError = Math.max(0, pw.length - 20);
     
        int numMiss = 1;
        int lwrMiss = 1;
        int uprMiss = 1;
        char prev = 0;
        int count = 0;

        for (char curr : pw) {
            // check repeating error
            if (curr == prev) {
                count++;
                continue;
            }
            if (count >= 3) {
                this.repeatingError.add(count);
            }
            count = 1;
            prev = curr;

            // check missing type error
            switch (Character.getType(curr)) {
                case Character.DECIMAL_DIGIT_NUMBER -> numMiss = 0;
                case Character.LOWERCASE_LETTER -> lwrMiss = 0;
                case Character.UPPERCASE_LETTER -> uprMiss = 0;
            }
        } if (count >= 3) {
            this.repeatingError.add(count);
        }
        // compute missing type error
        this.missTypeError = (numMiss + lwrMiss + uprMiss);
    }
}
