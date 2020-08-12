package LCPremium.Robinhood;

/**
 * Given a rows x cols screen and a sentence represented by a list of non-empty
 * words, find how many times the given sentence can be fitted on the screen.
 * <p>
 * Note:
 * <p>
 * A word cannot be split into two lines. The order of words in the sentence
 * must remain unchanged. Two consecutive words in a line must be separated by a
 * single space. Total words in the sentence won't exceed 100. Length of each
 * word is greater than 0 and won't exceed 10. 1 ≤ rows, cols ≤ 20,000. Example
 * 1:
 * <p>
 * Input: rows = 2, cols = 8, sentence = ["hello", "world"]
 * <p>
 * Output: 1
 * <p>
 * Explanation: hello--- world---
 * <p>
 * The character '-' signifies an empty space on the screen. Example 2:
 * <p>
 * Input: rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * <p>
 * Output: 2
 * <p>
 * Explanation: a-bcd- e-a--- bcd-e-
 * <p>
 * The character '-' signifies an empty space on the screen. Example 3:
 * <p>
 * Input: rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 * <p>
 * Output: 1
 * <p>
 * Explanation: I-had apple pie-I had--
 * <p>
 * The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenfitting {
    /**
     * String s = String.join(" ", sentence) + " " ;. This line gives us a
     * formatted sentence to be put to our screen. start is the counter for how
     * many valid characters from s have been put to our screen. if
     * (s.charAt(start % l) == ' ') is the situation that we don't need an extra
     * space for current row. The current row could be successfully fitted. So
     * that we need to increase our counter by using start++. The else is the
     * situation, which the next word can't fit to current row. So that we need
     * to remove extra characters from next word. start / s.length() is (# of
     * valid characters) / our formatted sentence.
     */

    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start - 1) % l) != ' ') {
                    start--;
                }
            }
        }

        return start / s.length();
    }

    public int wordsTypingDp(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length(), count = 0;
        int[] map = new int[len];
        for (int i = 1; i < len; ++i) {
            map[i] = s.charAt(i) == ' ' ? 1 : map[i - 1] - 1;
        }
        for (int i = 0; i < rows; ++i) {
            count += cols;
            count += map[count % len];
        }
        return count / len;
    }
}
