package Facebook;

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

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
