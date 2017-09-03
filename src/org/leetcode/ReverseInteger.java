package org.leetcode;

/**
 * Created by Raj on 9/3/17.
 */
public class ReverseInteger {

    public static int reverse(int x) {
        boolean sign = x > 0;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (res > Integer.MAX_VALUE / 10) return 0;
            res = res * 10 + x % 10;
            x = x / 10;
        }

        return sign ? res : -res;

    }

    public static void main(String[] args) {
        System.out.println(reverse(1000));
        System.out.println(reverse(1234));
        System.out.println(reverse(121));
    }
}
