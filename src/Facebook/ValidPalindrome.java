package Facebook;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome {
    //O(N) TC O(1) SC
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return isPalindrome(s, l, r - 1) || isPalindrome(s, l + 1, r);
            }
        }
        return true;
    }

    private boolean isPalindrome(String str, int s, int t) {
        while (s <= t) {
            if (str.charAt(s) == str.charAt(t)) {
                s++;
                t--;
            } else
                return false;
        }

        return true;
    }
}
