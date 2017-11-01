package com.epi.practice.ch5.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raj on 10/7/17.
 */
public class RemoveVowels {

    public static String reverseVowels(String s) {
        char[] list = s.toCharArray();
        int[] nums = new int[20];
        Set<Character> vowels = new HashSet<>(Arrays.asList
                (new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}));

        for (int i = 0, j = list.length - 1; i < j; ) {
            if (!vowels.contains(list[i])) {
                i++;
                continue;
            }
            if (!vowels.contains(list[j])) {
                j--;
                continue;
            }
            char temp = list[i];
            list[i] = list[j];
            list[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(list);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("Hell"));
    }
}
