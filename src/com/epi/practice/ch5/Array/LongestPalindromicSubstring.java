package com.epi.practice.ch5.Array;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raj on 11/21/17.
 */
public class LongestPalindromicSubstring {
    private int lo, maxLen;

    public static String longestPalindromeSubs(String a) {
        if (a == null || a.isEmpty()) {
            return null;
        }
        char[] ch = a.toCharArray();
        int longest = 1;
        Pair<Integer, Integer> result = new Pair<Integer, Integer>(0, 0);
        //Odd
        for (int i = 0; i < ch.length; i++) {
            int offset = 0;
            while (isValidIndex(ch, i - 1 - offset) && isValidIndex(ch, i + 1 + offset)
                    && ch[i - 1 - offset] == ch[i + 1 + offset]) {
                offset++;
            }
            int longestAtI = offset * 2 + 1;
            if (longestAtI > longest) {
                longest = longestAtI;
                result = new Pair<Integer, Integer>(i - offset, i + offset);
            }
        }

        //Even
        for (int i = 0; i < ch.length; i++) {
            int offset = 0;
            while (isValidIndex(ch, i - offset) && isValidIndex(ch, i + 1 + offset)
                    && ch[i - offset] == ch[i + 1 + offset]) {
                offset++;
            }
            int longestAtI = offset * 2 + 1;
            if (longestAtI > longest) {
                longest = longestAtI;
                result = new Pair<Integer, Integer>(i - offset + 1, i + offset);
            }
        }
        return a.substring(result.getKey(), result.getValue() + 1);
    }

    private static boolean isValidIndex(char[] a, int i) {
        if (a == null || i < 0 || i >= a.length)
            return false;
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
//        System.out.println(" String is : " + longestPalindromicSubstring.longestPalindrome("babad"));
//        System.out.println(" String is : " + longestPalindromicSubstring.longestPalindromeSubs("xybaabop"));
//        System.out.println(" String is : " + longestPalindromicSubstring.longestPalindromeSubs("abcivicyz"));
        System.out.println("Factor is" + longestPalindromicSubstring.getFactors(12));
    }

    public String longestPalindrome(String s) {
        int lo = 0, maxLen = 0;

        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    public void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (n <= 3) {
            return results;
        }

        getFactors(n, 2, new ArrayList<Integer>(), results);
        return results;
    }

    private void getFactors(int n, int start, List<Integer> current, List<List<Integer>> results) {
        if (n == 1) {
            if (current.size() > 1) {
                results.add(new ArrayList<Integer>(current));
            }
            return;
        }


        for (int i = start; i <= (int) Math.sqrt(n); i++) {  // ==> here, change 1
            if (n % i != 0) {
                continue;
            }
            current.add(i);
            getFactors(n / i, i, current, results);
            current.remove(current.size() - 1);
        }

        int i = n; // ===> here, change 2
        current.add(i);
        getFactors(n / i, i, current, results);
        current.remove(current.size() - 1);
    }
}
