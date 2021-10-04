package Linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a class which receives a list of words in the constructor,
 * and implements a method that takes two words word1 and word2 and
 * return the shortest distance between these two words in the list.
 * Your method will be called repeatedly many times with different parameters.
 * <p>
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance2 {
    private Map<String, List<Integer>> indexes;

    public ShortestWordDistance2(String[] words) {
        indexes = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (!indexes.containsKey(w)) {
                indexes.put(w, new ArrayList<Integer>());
            }
            indexes.get(w).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = indexes.get(word1);
        List<Integer> list2 = indexes.get(word2);
        int ret = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
            int index1 = list1.get(i), index2 = list2.get(j);
            if (index1 < index2) {
                ret = Math.min(ret, index2 - index1);
                i++;
            } else {
                ret = Math.min(ret, index1 - index2);
                j++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistance2 newShort = new ShortestWordDistance2(words);
        System.out.println(newShort.shortest("coding","practice"));
        System.out.println(newShort.shortest("coding","perfect"));

    }

}
