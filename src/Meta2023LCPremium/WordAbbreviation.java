package Meta2023LCPremium;

/**
 * A string can be abbreviated by replacing any number of non-adjacent,
 * non-empty substrings with their lengths. The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 *
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
 * Example 2:
 *
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 * Explanation: The word "apple" cannot be abbreviated as "a2e".
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 20
 * word consists of only lowercase English letters.
 * 1 <= abbr.length <= 10
 * abbr consists of lowercase English letters and digits.
 * All the integers in abbr will fit in a 32-bit integer.
 */
public class WordAbbreviation {
    /**
     * Approach
     *
     * Maintain the number in abbr in lastNum variable. As soon as you get a non-digit character,
     * calculate the len and reset the lastNum.
     *
     * For preceding 0s, checking if lastNum == 0 and current char is 0 then return false;
     *
     * For return condition check, we need to check word's length with len+lastNum because
     * if the abbr is ending with the digits then lastNum will have some value.
     *
     * -- Rakmo --
     *
     * Complexity
     *
     * Time complexity: O(abbr.length()) ~ O(n)
     * Space complexity: O(1)
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        if(abbr.length() == 0 || word.length() < abbr.length()) return false;
        int len = 0, lastNum = 0;

        for(int i=0; i<abbr.length(); i++){
            char c = abbr.charAt(i);
            if(lastNum == 0 && c == '0')
                return false;

            if(Character.isDigit(c)){
                lastNum = lastNum*10 + c-'0';
            }
            else {
                len = len + lastNum + 1;
                lastNum = 0;

                if(word.length() >= len){
                    if(word.charAt(len-1) != c)
                        return false;
                }
            }
        }

        return len+lastNum == word.length();

    }
}
