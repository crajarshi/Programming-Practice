package Meta2023LCPremium.ArraysAndStrings;

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
    public static boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || abbr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int wLen = word.length();
        int aLen = abbr.length();

        // length of abbreviation cannot be greater than word's length
        if (aLen > wLen) {
            return false;
        }

        if (wLen == 0) {
            return true;
        }

        int i = 0;
        int j = 0;

        while (i < wLen && j < aLen) {
            // It current characters in both word and abbr is same continue checking.
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            // Now current characters in word and abbr do not match. Thus current character
            // in abbr should be a valid starting digit 0 < x <= 9.
            if (abbr.charAt(j) == '0' || !Character.isDigit(abbr.charAt(j))) {
                return false;
            }

            // The num value
            int num = 0;
            while (j < aLen && Character.isDigit(abbr.charAt(j))) {
                num = 10 * num + (abbr.charAt(j) - '0');
                j++;
            }

            // Increment word pinter by num.
            i += num;
        }

        // If both i and j pointers are at end, then we have a valid word abbreviation
        return i == wLen && j == aLen;
    }

    public static void main(String[] args) {
        validWordAbbreviation("internationalization","i12iz4n");
    }
}
