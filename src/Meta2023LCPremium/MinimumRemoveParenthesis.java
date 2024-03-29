package Meta2023LCPremium;

import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')',
 * in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 */
public class MinimumRemoveParenthesis {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();

        char ch[] = s.toCharArray();

        for(int i = 0; i < ch.length; i++){
            if(ch[i] == '(') st.push(i);
            else if(ch[i] == ')'){
                if(st.isEmpty()) ch[i] = '#';
                else st.pop();
            }
        }
        while(!st.isEmpty()){
            ch[st.pop()] = '#';
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < ch.length; i++){
            if(ch[i] != '#') sb.append(ch[i]);
        }
        return sb.toString();
    }

    /** Approach 2
     *
     */
    public String minRemoveToMakeValid2(String s) {
        char[] arr = s.toCharArray();
        int open = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(')
                open++;
            else if (arr[i] == ')') {
                if (open == 0)
                    arr[i] = '*';
                else
                    open--;
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (open > 0 && arr[i] == '(') {
                arr[i] = '*';
                open--;
            }

        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < ch.length; i++){
            if(ch[i] != '#') sb.append(ch[i]);
        }
        return sb.toString();
    }
}
