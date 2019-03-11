package leetcode;

import java.util.HashMap;

/**
 * Created by Raj on 3/10/19.
 */
public class MinimumWIndowSubstring {
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }

        int begin = 0;
        int end = 0;
        int count = t.length();
        int head = 0, tail = 0;
        int diff = Integer.MAX_VALUE;
        while (end < s.length()) {
            char e = s.charAt(end);
            //end value manipulation
            if (map.merge(e, -1, Integer::sum) >= 0) { // map.merge() returns the final result
                count--;
            }
            while (count == 0) {
                char b = s.charAt(begin);

                if (diff > end - begin) {
                    head = begin;
                    // +1 because of exclusive s.substring() call below
                    tail = end + 1;
                    diff = tail - head;
                }
                //begin value manipulation
                if (map.merge(b, +1, Integer::sum) > 0) { // map.merge() returns the final result
                    count++;
                }
                begin++;
            }
            end++;
        }

        return s.substring(head, tail);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        ;
    }
}
