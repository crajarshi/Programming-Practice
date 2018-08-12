package leetcode;

/**
 * Follow normal way (two pointers) to check if s is palindrome. When two chars are not equal,
 * try to skip (pseudo delete) either left one or right one and continue checking.
 */
public class ValidPalindrome2 {
    public static boolean validPalindrome(String s) {
        int l = -1, r = s.length();
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return isPalindromic(s, l, r + 1) || isPalindromic(s, l - 1, r);
        return true;
    }

    public static boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
    }
}
