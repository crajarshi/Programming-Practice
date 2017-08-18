package org.String;

public class StringtoInt {

    public static int stringToInt(String s) {
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); ++i) {
            final int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return s.charAt(0) == '-' ? -result : result;
    }

    public static void main(String[] args) {
//        System.out.println(stringToInt("12356"));
        System.out.println(stringToInt("Raj"));
    }
}
