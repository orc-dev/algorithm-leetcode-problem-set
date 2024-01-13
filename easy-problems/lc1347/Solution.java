/**
 * @author: orc-dev
 * @update: Jan.12 2024
 * 
 * @leetcode: 1347. Minimum Number of Steps to Make Two Strings Anagram
 * @tag: hash table, counting
 */
class Solution {
    public int minSteps(String s, String t) {
        // Count frequency for each String
        final int[] freqS = getFreq(s.toCharArray());  
        final int[] freqT = getFreq(t.toCharArray());

        // Computes the difference of each char in two Strings
        final int[] diff = new int[26];
        for (int i = 0; i < freqS.length; ++i) {
            diff[i] = freqS[i] - freqT[i];
        }

        // Sum up positive and negative frequency, reslectively
        int pos = 0;
        int neg = 0;
        for (int i = 0; i < diff.length; ++i) {
            pos += Math.max(diff[i], 0);
            neg += Math.min(diff[i], 0);
        }
        // Min step is the larger one
        return Math.max(pos, neg);
    }

    /** Returns the frequency table of chars in 'src'. */
    private int[] getFreq(char[] src) {
        int[] freq = new int[26];
        for (char x : src) {
            freq[x - 'a']++;
        }
        return freq;
    }
}