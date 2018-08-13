package leetcode;

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
     * Key idea, every time we move to right, we only need to consider whether using this new character
     * as tail could produce
     * new palindrome string of length (current length +1) or (current length +2)
     * <p>
     * Example: "xxxbcbxxxxxa", (x is random character, not all x are equal) now we
     * are dealing with the last character 'a'. The current longest palindrome
     * is "bcb" with length 3.
     * 1. check "xxxxa" so if it is palindrome we could get a new palindrome of length 5.
     * 2. check "xxxa" so if it is palindrome we could get a new palindrome of length 4.
     * 3. do NOT check "xxa" or any shorter string since the length of the new string is
     * no bigger than current longest length.
     * 4. do NOT check "xxxxxa" or any longer string because if "xxxxxa" is palindrome
     * then "xxxx" got  from cutting off the head and tail is also palindrom. It has
     * length > 3 which is impossible.'
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int curLen = 0;
        int start = -1;
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (isPalindrome(array, i - curLen - 1, i)) {
                start = i - curLen - 1;
                curLen += 2;
            } else if (isPalindrome(array, i - curLen, i)) {
                start = i - curLen;
                curLen += 1;
            }
        }
        return new String(array, start, curLen);
    }

    private static boolean isPalindrome(char[] array, int start, int end) {
        if (start < 0) {
            return false;
        }
        while (start < end) {
            if (array[start++] != array[end--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }


}
