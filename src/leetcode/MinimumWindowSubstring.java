package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in
 * T in complexity O(n).
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

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int lo = 0;
        int hi = 0;
        while (hi < s.length()) {
            map.put(s.charAt(hi), map.getOrDefault(s.charAt(hi), 0) + 1);
            if (map.size() > k) { // need to slide
                if (map.get(s.charAt(lo)) == 1)
                    map.remove(s.charAt(lo));
                else
                    map.put(s.charAt(lo), map.get(s.charAt(lo)) - 1);
                lo++;
                hi++;
            } else { // need to extend
                hi++;
            }
        }
        return hi - lo;
    }

    public static String minWindowEasy(String s, String t) {
        if(s == null || t == null || s.length() == 0 || t.length() == 0) return "";

        char[] input = s.toCharArray();

        // elementBalance[i]  =    # of element i that's required for a "valid" window
        // elementBalance[i] < 0  We saw element i less times than required in the current window
        // elementBalance[i] == 0 We saw element i exactly as many times as required in the current window
        // elementBalance[i] > 0  We saw element i more times than required in the current window
        // Note: We only care about elementBalance of required elements and ignore the balance of other elements that were found

        int[] elementBalance = new int[128];

        // Update balances of required elements
        for(char requiredElement : t.toCharArray()) {
            elementBalance[requiredElement]--; // Negative because we haven't found them yet
        }

        // Total # of required elements found in the current window
        int overallBalance = -t.length(); // Negative because we haven't found them yet

        // Tracks the min window found so far
        int minWindowStartIndex = 0;
        int minWindowLength = Integer.MAX_VALUE;

        int curWindowStartIndex = 0;
        for(int curWindowEndIndex=0; curWindowEndIndex < input.length; curWindowEndIndex++) {
            char curElement = input[curWindowEndIndex];
            if(elementBalance[curElement] < 0) { // If this is a required element
                overallBalance++; // We have found a required element
            }
            elementBalance[curElement]++;
            // While all required elements are in the current window
            while(overallBalance == 0) {
                // Updated minimum window found if needed
                int curWindowLength = curWindowEndIndex-curWindowStartIndex+1;
                if(curWindowLength < minWindowLength) {
                    minWindowLength = curWindowLength;
                    minWindowStartIndex = curWindowStartIndex;
                }
                // Shrink (minimize) this window size by incrementing the start index
                char elementToRemove = input[curWindowStartIndex];
                curWindowStartIndex++;
                if(elementBalance[elementToRemove] == 0) { // We're losing the minimum required# of this element
                    overallBalance--;
                }
                elementBalance[elementToRemove]--;
            }
        }
        return minWindowLength == Integer.MAX_VALUE ? "" : s.substring(minWindowStartIndex,minWindowStartIndex+minWindowLength);
    }

    public static void main(String[] args) {
        System.out.println(minWindowEasy("ADOBECODEBANC", "ABC"));
//        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
