package Facebook;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * <p>
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 */
public class PermutationInString {
    /**
     * How do we know string p is a permutation of string s? Easy, each character in p is in s too.
     * So we can abstract all permutation strings of s to a map (Character -> Count). i.e. abba -> {a:2, b:2}.
     * Since there are only 26 lower case letters in this problem, we can just use an array to represent the map.
     * How do we know string s2 contains a permutation of s1? We just need to create a sliding window with length of s1,
     * move from beginning to the end of s2. When a character moves in from right of the window, we subtract 1
     * to that character count from the map. When a character moves out from left of the window, we add 1 to that character count.
     * So once we see all zeros in the map, meaning equal numbers of every characters between s1 and the substring in the sliding window, we know the answer is true.
     */
    //O(l1 + (l2 - l1))
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }

        return false;
    }

    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
}
