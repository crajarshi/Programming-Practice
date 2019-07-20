package Facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary)
 * +, -, or * between the digits so they evaluate to the target value.
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 * <p>
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 * <p>
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 */
public class ExpressionAddOperator {
    /**
     * Let's quickly look at the steps involved in our backtracking algorithm before looking at the pseudo-code.
     * <p>
     * As discussed above, we have multiple choices of what operators to use and what the operands can be and hence,
     * we have to look at all the possibilities to find all valid expressions.
     * Our recursive call will have an index which represents the current digit
     * we're looking at in the original nums string and also the expression string built till now.
     * At every step, we have exactly 4 different recursive calls.
     * The NO OP call simply extends the current_operand by the current digit and moves ahead.
     * Rest of the recursive calls correspond to +, -, and *.
     * We keep on building our expression like this and eventually, the entire nums string would be processed.
     * At that time we check if the expression we built till now is a valid expression or not and we record it if it is a valid one.
     * 1. procedure recurse(digits, index, expression):
     * 2.     if we have reached the end of the string:
     * 3.         if the expression evaluates to the target:
     * 4.             Valid Expression found!
     * 5.     else:
     * 6.         try out operator 'NO OP' and recurse
     * 7.         try out operator * and recurse
     * 8.         try out operator + and recurse
     * 9.         try out operator - and recurse
     */
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if (num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (target == eval)
                rst.add(path);
            return;
        }
        /**
         * overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
         0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
         a little trick is that we should save the value that is to be multiplied in the next recursion
         */
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }
}
