package Facebook;

/**
 * In an alien language, surprisingly they also use english lowercase
 * letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language,
 * and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * <p>
 * Example 2:
 * <p>
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * <p>
 * Example 3:
 * <p>
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.)
 * According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are english lowercase letters.
 */
public class VerifyingAlienDictionary {
    /**
     * Since we are checking character by character,
     * once we find that at a certain index of both the words
     * it is satisfying the lexicographical rule,
     * there is no point in checking further and hence we make length = -1
     * so that the immediate for loop with variable j
     * terminates and we can continue with next 2 adjacent words.
     */

    public static boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); i++)
            index[order.charAt(i) - 'a'] = i;
        for (int i = 0; i < words.length - 1; i++) {
            int length = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < length; j++)
                if (words[i].charAt(j) != words[i + 1].charAt(j))
                    if (index[words[i].charAt(j) - 'a'] > index[words[i + 1].charAt(j) - 'a']) {
                        System.out.println(index[words[i].charAt(j) - 'a']);
                        System.out.println(index[words[i + 1].charAt(j) -
                                'a']);
                        return false;
                    } else
                        length = -1;
            if (length != -1 && words[i].length() > words[i + 1].length())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] str = {"word", "world", "row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(isAlienSorted(str, order));
    }
}
