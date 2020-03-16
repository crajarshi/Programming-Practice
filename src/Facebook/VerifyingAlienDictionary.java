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
                    if (index[words[i].charAt(j) - 'a'] > index[words[i + 1].charAt(j) - 'a'])
                        return false;
                    else
                        length = -1;
            if (length != -1 && words[i].length() > words[i + 1].length())
                return false;
        }
        return true;
    }

    /**
     * Build a transform mapping from order,
     * Find all alien words with letters in normal order.
     * <p>
     * For example, if we have order = "xyz..."
     * We can map the word "xyz" to "abc" or "123"
     * <p>
     * Then we check if all words are in sorted order.
     * <p>
     * Complexity
     * Time O(NS)
     * Space O(1)
     * <p>
     * N is the length of words since we go through the words list to compare each string with its previous one.
     * S is the max length of word in the words list since in the helper function, the worst case is encounter the max string.
     * I guess that's the meaning of N and S.
     *
     * @param words
     * @param order
     * @return
     */

    public static boolean isAlienSortedbetter(String[] words, String order) {
        int[] dict = new int[26];
        for (int i = 0; i < dict.length; i++) {
            int idx = order.charAt(i) - 'a';
            dict[idx] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1], dict) > 0) return false;
        }

        return true;
    }

    private static int compare(String word1, String word2, int[] dict) {
        int L1 = word1.length();
        int L2 = word2.length();
        int min = Math.min(L1, L2);
        for (int i = 0; i < min; i++) {
            int c1 = word1.charAt(i) - 'a';
            int c2 = word2.charAt(i) - 'a';
            if (c1 != c2)
                return dict[c1] - dict[c2];
        }
        return L1 == min ? -1 : 1;
    }

    public static void main(String[] args) {
        String[] str = {"word", "world", "row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(isAlienSortedbetter(str, order));
    }
}
