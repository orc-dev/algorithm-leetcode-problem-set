/**
 * @update: Jan.9 2024
 * 
 * @Leetcode Idea:
 *   - Convert half of the value
 *   - When reversed value > original value, check two cases
 */
class Solution {
    public boolean isPalindrome(int x) {
        // negative numbers are obviously not palindrome
        if (x < 0)
            return false;

        int rev = 0;
        while (rev < x) {
            rev = (rev * 10) + (x % 10);
            // all positive numbers with ending zeros are not palindrome
            if (rev == 0) {
                return false;
            }
            x /= 10;
        }
        return (x == rev) || (x == rev / 10);
    }
}