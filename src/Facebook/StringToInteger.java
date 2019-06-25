package Facebook;

/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical
 * value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 * <p>
 * Input: "42"
 * Output: 42
 * Example 2:
 * <p>
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 * <p>
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 * <p>
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * <p>
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 */
public class StringToInteger {

    public int myAtoi(String str) {
        str = str.trim(); //remove leading whitespace

        // Create a StringBuilder to build out a resulting number
        StringBuilder numBuilder = new StringBuilder();
        char c;

        // Loop through the string, one character at a time.
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            // If it is the first character and it is '-', add it to the string, if it is '+', ignore it.
            // Otherwise, if the character is a Digit add it to the string.
            // If it is not a digit or the first character, break out of the loop and stop building the string.
            if ((i == 0 && c == '-') || Character.isDigit(c)) {
                numBuilder.append(c);
            } else if (i == 0 && c == '+') {
                continue;
            } else {
                break;
            }
        }

        // Build the string, return 0 if it is empty
        String outStr = numBuilder.toString();
        if (outStr.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(outStr);
        } catch (NumberFormatException nfe) {

            // Rules for MAX_VALUE, MIN_VALUE, and a catch-all rule.
            if (Character.isDigit(outStr.charAt(0))) {
                return Integer.MAX_VALUE;
            } else if (outStr.charAt(0) == '-' && outStr.length() > 1) {
                return Integer.MIN_VALUE;
            } else {
                return 0;
            }
        }
    }


}
