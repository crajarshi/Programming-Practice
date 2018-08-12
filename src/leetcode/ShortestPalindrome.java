package leetcode;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * Example 1:
 * <p>
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome {
    public static String shortestPalindrome(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        if (j == s.length()) {
            return s;
        }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }

    /**
     * Iterative version.
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome2(String s) {
        StringBuilder res = new StringBuilder();
        int j = 0, end = s.length();
        while (true) {
            j = 0;
            for (int i = end - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) j++;
            }
            if (j == end) break;
            end = j;
        }
        res.append(s.substring(end, s.length())).reverse().append(s.substring(0, end)).append(s.substring(end, s.length()));
        return res.toString();
    }

    public static void main(String[] args) {
//        System.out.println(shortestPalindrome("abcd"));
        System.out.println(shortestPalindrome2("abcd"));
    }
}
