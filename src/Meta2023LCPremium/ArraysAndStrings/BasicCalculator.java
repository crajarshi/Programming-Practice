package Meta2023LCPremium.ArraysAndStrings;
/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        int result = 0;
        //proceed only if the input is valid
        if(s != null && s.length() > 0){
            Stack<Integer> nums = new Stack();
            char lastOperator = '+'; //start with +
            int num = 0; //start with 0
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                //find the number , if its a double digit
                if(Character.isDigit(c)){
                    num = num*10 + c - '0';
                }
                //if the character is operator OR last character
                if(isOperator(c) || i == s.length()-1){
                    //if the last operation was addition, add the number to stack
                    if(lastOperator == '+'){
                        nums.push(num);
                    }
                    //if the last operation was deletion, add the negative of the number to stack
                    else if(lastOperator == '-'){
                        nums.push(-1*num);
                    }
                    /*if the last operation was multiplication or division,
                    pop the last number from stack and perform the operation on
                    current number and the number from stack and
                    add the result back to the stack.
                    */
                    else if(lastOperator == '*'){
                        nums.push(nums.pop()* num);
                    }else if(lastOperator == '/'){
                        nums.push(nums.pop()/ num);
                    }
                    //set the number back to zero
                    num = 0;
                    //set the current operation as last operation
                    lastOperator = c;
                }
            }
            /*
            at this point , the stack will have only the numbers that need to be added.
            get those from stack and add to the result.
            */
            while(!nums.isEmpty()){
                result = result + nums.pop();
            }
        }
        return result;
    }
    private boolean isOperator(char c){
        return c == '+' || c == '-' ||
                c == '*' || c== '/';
    }

}
