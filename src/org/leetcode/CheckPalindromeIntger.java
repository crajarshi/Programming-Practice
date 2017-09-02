package org.leetcode;

/**
 * Created by Raj on 9/2/17.
 */
public class CheckPalindromeIntger {

    public static boolean checkPalindromeInteger(Integer palindromeOrNot){
        if (palindromeOrNot < 0 || (palindromeOrNot/10 == 0 && palindromeOrNot != 0) )
            return false;
        int reversedNumber = 0;
        while (palindromeOrNot > reversedNumber){
            reversedNumber = reversedNumber * 10 + palindromeOrNot %10;
            palindromeOrNot /= 10;
        }

        return (palindromeOrNot == reversedNumber) || (palindromeOrNot == reversedNumber/10);
    }

    public static void main(String[] args) {
        System.out.println(checkPalindromeInteger(121));
        System.out.println(checkPalindromeInteger(1221));
        System.out.println(checkPalindromeInteger(134561));
    }
}
