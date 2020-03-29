package Facebook;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        int[] arr = new int[128];
        //initialize frequency table for t
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i)]++;
        }
        //initialize sliding window
        int counter = t.length(), left = 0, right = 0, window = Integer.MAX_VALUE, head = -1;

        //Start Sliding window
        while (right < s.length()) {
            //if current char found in table, decrement count
            char rc = s.charAt(right++);
            if (arr[rc] > 0) {
                counter--;
            }
            arr[rc]--;

            // if counter ==0 means we found an answer , now
            while (counter == 0) {
                //store new answer if smaller that previously
                if (right - left < window) {
                    window = right - (head = left);
                }
                //
                char lc = s.charAt(left++);
                if (arr[lc] == 0) {
                    counter++;
                }
                arr[lc]++;
            }
        }
        return head == -1 ? "" : s.substring(head, head + window);
    }

    public static List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length()) return result;
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, counter = t.length();

        while (end < s.length()) {
            final char c1 = s.charAt(end++);
            if (map[c1] > 0) counter--;
            map[c1]--;

            while (counter == 0) {
                if (end - start == t.length()) {
                    result.add(start);
                }
                final char c2 = s.charAt(start++);
                map[c2]++;
                if (map[c2] > 0) counter++;

            }

        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }

    //O(l1) + O(l2)
    public String minWindow2(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            final char c1 = s.charAt(end++);
            if (map[c1]-- > 0) counter--;
//            map[c1]--;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                final char c2 = s.charAt(start++);
                map[c2]++;
                if (map[c2] > 0) counter++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0;
        if (s.length() > 0) {
            int[] map = new int[256];
            int start = 0, end = 0, counter = 0;

            while (end < s.length()) {
                final char c1 = s.charAt(end++);
                if (map[c1] == 0) counter++;
                map[c1]++;

                while (counter > k) {
                    final char c2 = s.charAt(start++);
                    if (map[c2] == 1) counter--;
                    map[c2]--;
                }

                maxLen = Math.max(maxLen, end - start);
            }

        }
        return maxLen;
    }

    // Find substring with 0 distinct charcters
    public int lengthOfLongestSubstring2(String s) {
        int[] map = new int[128];
        int start = 0, end = 0, maxLen = 0, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end++);
            if (map[c1] > 0) counter++;
            map[c1]++;
//            end++;

            while (counter > 0) {
                final char c2 = s.charAt(start++);
                if (map[c2] > 1) counter--;
                map[c2]--;
//                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
}
