package Facebook;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    /**
     * We observe that a palindrome mirrors around its center.
     * Therefore, a palindrome can be expanded from its center, and there are only 2n - 12n−1 such centers.
     * <p>
     * You might be asking why there are 2n - 1 but not n^2 centers?
     * The reason is the center of a palindrome can be in between two letters.
     * Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     */
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        if (maxLen < end - start - 1) {
            lo = start + 1;
            maxLen = end - start - 1;
        }
    }
}
