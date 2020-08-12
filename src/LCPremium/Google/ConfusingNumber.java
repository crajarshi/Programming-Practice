package LCPremium.Google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9
 * are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3,
 * 4, 5 and 7 are rotated 180 degrees, they become invalid.
 * <p>
 * A confusing number is a number that when rotated 180 degrees becomes a
 * different number with each digit valid.(Note that the rotated number can be
 * greater than the original number.)
 * <p>
 * Given a positive integer N, return the number of confusing numbers between 1
 * and N inclusive.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 20 Output: 6 Explanation: The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9. 9 converts to 6. 10 converts to 01 which is just 1. 16
 * converts to 91. 18 converts to 81. 19 converts to 61. Example 2:
 * <p>
 * Input: 100 Output: 19 Explanation: The confusing numbers are
 * [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 10^9
 */
public class ConfusingNumber {
    static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
    }

    int res = 0;
    int[] digits = new int[]{0, 1, 6, 8, 9};
    int[] rotate = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    public int confusingNumberII(int N) {
        // begin with cur = 0
        helper(N, 0);
        return res;
    }

    private void helper(int N, long cur) {
        if (isConfusingNumber(cur)) {
            res++;
        }

        /*
         * Iterate through all of possible numbers in dfs
         * Ex : when N = 22
         * 1
         * 10
         * 11
         * 16
         * 18
         * 19  // stop here as this is last possible value before 22
         *
         * 6 // No 2 digit value starting with 6 is < 22
         * 8
         * 9
         */
        for (Integer i : map.keySet()) {
            long next = cur * 10 + i;
            if (next <= N && next != 0) {
                //System.err.println(next);
                helper(N, next);
            }
        }
    }

    private boolean isConfusingNumber(long cur) {
        // lets say curr = 69
        long src = cur;
        long strobogrammticRotatedNumber = 0;
        /* Since we rotate 180, least significant digit would become most significant digit in strobogrammatic number.
         * find each digit starting from least significant digit and get strobogrammatic digit. */
        while (cur > 0) {

            strobogrammticRotatedNumber = strobogrammticRotatedNumber * 10 + map.get((int) cur % 10); // 0*10+9%10 6 = 6   next loop 6*10 + 9 69
            //System.err.println(cur +" result = "+ strobogrammticRotatedNumber);
            cur /= 10;                                // 6            next loop
        }
        return strobogrammticRotatedNumber != src;
    }

    //The basic idea is to search all numbers composed of 0, 1, 6, 8, 9 and check each of them before they grow bigger than N.
    public int confusingNumberIIa(int N) {
        int res = 0;

        List<Integer> list = new ArrayList<>();
        list.add(0);

        boolean found = false;

        while (!found) {
            List<Integer> tmp = new ArrayList<>();
            for (Integer n : list) {

                for (int i = 0; i < 5; i++) {
                    int nn = n * 10 + digits[i];

                    if (nn > N) {
                        found = true;
                        break;
                    }
                    if (nn != 0) {
                        tmp.add(nn);
                    }
                    if (isConfusing(nn)) {
                        res++;
                    }
                }
                if (found) {
                    break;
                }
            }
            list = tmp;
        }

        return res;
    }

    private boolean isConfusing(int num) {
        int tmp = num;
        int rot = 0;
        while (tmp > 0) {
            int d = tmp % 10;
            rot = rot * 10 + rotate[d];
            tmp /= 10;
        }
        return rot != num;
    }
}
