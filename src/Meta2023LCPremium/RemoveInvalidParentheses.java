package Meta2023LCPremium;

import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Example 1:
 * <p>
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 * <p>
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 * <p>
 * Input: ")("
 * Output: [""]
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> output = new ArrayList<>();
        removeHelper(s, output, 0, 0, '(', ')');
        return output;
    }

    /**
     * Explanation:
     * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
     * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
     *
     * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix.
     * However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2]
     * but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
     *
     * After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string.
     * However, we need to keep another information: the last removal position. If we do not have this position,
     * we will generate duplicate by removing two ‘)’ in two steps only with a different order.
     * For this, we keep tracking the last removal position and only remove ‘)’ after that.
     *
     * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
     * The answer is: do the same from right to left.
     * However a cleverer idea is: reverse the string and reuse the code!
     * Here is the final implement in Java.
     * @param s
     * @param output
     * @param iStart
     * @param jStart
     * @param openParen
     * @param closedParen
     */

    public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {
        int numOpenParen = 0, numClosedParen = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openParen) numOpenParen++;
            if (s.charAt(i) == closedParen) numClosedParen++;
            if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
                for (int j = jStart; j <= i; j++) // Try removing one at each position, skipping duplicates
                    if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen))
                        // Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
                        removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), output, i, j, openParen, closedParen);
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(s).reverse().toString();
        if (openParen == '(')
            removeHelper(reversed, output, 0, 0, ')', '(');
        else
            output.add(reversed);
    }

    /**
     * The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ),
     * check if they are valid, if found valid ones on the current level, put them to the final result list and we are done,
     * otherwise, add them to a queue and carry on to the next level.
     * <p>
     * The good thing of using BFS is that we can guarantee the number of parentheses
     * that need to be removed is minimal, also no recursion call is needed in BFS.
     * <p>
     * Thanks to @peisi, we don't need stack to check valid parentheses.
     * <p>
     * Time complexity:
     * <p>
     * In BFS we handle the states level by level, in the worst case, we need to handle all the levels,
     * we can analyze the time complexity level by level and add them up to get the final complexity.
     * <p>
     * On the first level, there's only one string which is the input string s,
     * let's say the length of it is n, to check whether it's valid, we need O(n) time.
     * On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings,
     * each of them has n-1 characters, and for each string, we need to check whether it's valid or not,
     * thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level,
     * total time complexity is (n-2) x C(n, n-2), so on and so forth...
     * <p>
     * Finally we have this formula:
     * <p>
     * T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
     *
     * Regarding the time complexity, I think one way we can think about the search space is as a power subset of the original string.
     * So it includes all possible substrings from 0 character to N(number of chars in the input string) characters.
     * So the possibilities are 2^n. (we either pick a character or don't pick it)
     * For each subset we check if it is a valid string so it becomes n*(2^n)
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;//post decrement
        }

        return count == 0;
    }
}

