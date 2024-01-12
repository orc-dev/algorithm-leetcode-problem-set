/**
 * @author: orc-dev
 * @upadte: Jan.11 2024
 * 
 * @leetcode: 1704. Determine if String Halves Are Alike
 * @tag: string, counting
 * 
 * Performance
 *   - Runtime: (..)
 *   - Memory: (..)
 */
class Solution {
    public boolean halvesAreAlike(String s) {
        final char[] seq = s.toCharArray();
        int i = 0;
        int j = seq.length - 1;
        int count = 0;

        while (i < j) {
            // Update count
            count += isVowel(seq[i]);
            count -= isVowel(seq[j]);
            // Update pointers
            i++;
            j--;
        }
        return count == 0;
    }

    /** Returns 1 if a char is a vowel, otherwise 0. */
    private int isVowel(char ch) {
        return switch(ch) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> 1;
            default -> 0;
        };
    }
}
