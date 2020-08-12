package LCPremium.Google;

import java.util.Arrays;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by
 * reusing the current digits. There is no limit on how many times a digit can
 * be reused.
 * <p>
 * You may assume the given input string is always valid. For example, "01:34",
 * "12:09" are all valid. "1:34", "12:9" are all invalid.
 * <p>
 * Example 1:
 * <p>
 * Input: "19:34" Output: "19:39" Explanation: The next closest time choosing
 * from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not
 * 19:33, because this occurs 23 hours and 59 minutes later. Example 2:
 * <p>
 * Input: "23:59" Output: "22:22" Explanation: The next closest time choosing
 * from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is
 * next day's time since it is smaller than the input time numerically.
 */
public class NextClosestTime {
    /**
     * This approach here is trying to find next digit for each postion in
     * "HH:MM" from right to left. If the next digit is greater than current
     * digit, return directly and keep other digits unchanged. Here is the
     * steps: (e.g. "17:38")
     * <p>
     * Retrieve all four digits from given string and sort them in asscending
     * order, "17:38" -> digits[] {'1', '3', '7', '8'}
     * <p>
     * Apply findNext() from the right most digit to left most digit, try to
     * find next greater digit from digits[] (if exist) which is suitable for
     * current position, otherwise return the minimum digit (digits[0]):
     * <p>
     * "HH:M_": there is no upperLimit for this position (0-9). Just pick the
     * next different digit in the sequence. In the example above, '8' is
     * already the greatest one, so we change it into the smallest one
     * (digits[0] i.e. '1') and move to next step: "17:38" -> "17:31"
     * <p>
     * "HH:_M": the upperLimit is '5' (00~59). The next different digit for '3'
     * is '7', which is greater than '5', so we should omit it and try next.
     * Similarly, '8' is beyond the limit, so we should choose next, i.e. '1':
     * "17:38" -> "17:11"
     * <p>
     * "H_:MM": the upperLimit depends on result[0]. If result[0] == '2', then
     * it should be no more than '3'; else no upperLimit (0-9). Here we have
     * result[0] = '1', so we could choose any digit we want. The next digit for
     * '7' is '8', so we change it and return the result directly. "17:38" ->
     * "18:11"
     * <p>
     * "_H:MM": the upperLimit is '2' (00~23). e.g. "03:33" -> "00:00"
     */

    public String nextClosestTime(String time) {
        char[] result = time.toCharArray();
        char[] digits = new char[]{result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);

        // find next digit for HH:M_
        result[4] = findNext(result[4], (char) ('9' + 1), digits);  // no upperLimit for this digit, i.e. 0-9
        if (result[4] > time.charAt(4))
            return String.valueOf(result);  // e.g. 23:43 -> 23:44

        // find next digit for HH:_M
        result[3] = findNext(result[3], '5', digits);
        if (result[3] > time.charAt(3))
            return String.valueOf(result);  // e.g. 14:29 -> 14:41

        // find next digit for H_:MM
        result[1] = result[0] == '2' ? findNext(result[1], '3', digits) : findNext(result[1], (char) ('9' + 1), digits);
        if (result[1] > time.charAt(1))
            return String.valueOf(result);  // e.g. 02:37 -> 03:00

        // find next digit for _H:MM
        result[0] = findNext(result[0], '2', digits);
        return String.valueOf(result);  // e.g. 19:59 -> 11:11
    }

    /**
     * find the next bigger digit which is no more than upperLimit. If no such
     * digit exists in digits[], return the minimum one i.e. digits[0]
     *
     * @param current    the current digit
     * @param upperLimit the maximum possible value for current digit
     * @param digits[]   the sorted digits array
     * @return
     */
    private char findNext(char current, char upperLimit, char[] digits) {
        //System.out.println(current);
        if (current == upperLimit) {
            return digits[0];
        }
        int pos = Arrays.binarySearch(digits, current) + 1;
        while (pos < 4 && (digits[pos] > upperLimit || digits[pos] == current)) { // traverse one by one to find next greater digit
            pos++;
        }
        return pos == 4 ? digits[0] : digits[pos];
    }


    /**
     * char[] res = time.toCharArray();
     *         Character[] digits = new Character[]{res[0],res[1],res[3],res[4]};
     *         TreeSet<Character> set = new TreeSet<Character>(Arrays.asList(digits));
     *
     *         res[4] = next(set,res[4],'9');
     *         if(time.charAt(4) < res[4]) return new String(res);
     *
     *         res[3] = next(set,res[3],'5');
     *         if(time.charAt(3) < res[3]) return new String(res);
     *
     *         res[1] = next(set,res[1], res[0] == '2' ? '3' : '9');
     *         if(time.charAt(1) < res[1]) return new String(res);
     *
     *         res[0] = next(set,res[0],'2');
     *         return new String(res);
     *     }
     *
     *     private char next(TreeSet<Character> set, char c, char limit){
     *         Character n = set.higher(c);
     *         return n == null || n > limit ? set.first() : n;
     */
}
