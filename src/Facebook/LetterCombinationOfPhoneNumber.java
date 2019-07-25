package Facebook;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationOfPhoneNumber {

    public static Map<String, String> phone = new HashMap<String, String>() {{
        put("0", "0");
        put("1", "1");
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public static List<String> output = new ArrayList<String>();

    /**
     * This is a iterative solution. For each digit added, remove and copy every element in the queue and add the possible letter to each element,
     * then add the updated elements back into queue again. Repeat this procedure until all the digits are iterated.
     * <p>
     * I did a experiment to compare backtracking(DFS) method and this iterative method. It turns out iterative one is 4 times faster.
     * T C - O(3^N * 4 ^ M) where N is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8)
     * and M is the number of digits in the input that maps to 4 letters (e.g. 7, 9), and N+M is the total number digits in the input.
     */
    //O(3 ^ N * 4 ^N) both TC AND SC.
    public static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    public static void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public static List<String> letterCombinationsbacktrack(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }


    public static void main(String[] args) {
        System.out.println(letterCombinationsbacktrack("123"));
        System.out.println(letterCombinations("123"));
    }

}
