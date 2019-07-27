package Facebook;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {
    /**
     * The intuition behind this approach is that the given problem (ss) can be divided into subproblems s1s1 and s2s2.
     * If these subproblems individually satisfy the required conditions, the complete problem, ss also satisfies the same. e.g. "
     * \text{catsanddog}catsanddog" can be split into two substrings "\text{catsand}catsand", "\text{dog}dog". The subproblem "\text{catsand}catsand"
     * can be further divided into "\text{cats}cats","\text{and}and", which individually are a part of the dictionary making "\text{catsand}catsand"
     * satisfy the condition. Going further backwards, "\text{catsand}catsand", "\text{dog}dog" also satisfy the required criteria individually
     * leading to the complete string "\text{catsanddog}catsanddog" also to satisfy the criteria.
     * <p>
     * Now, we'll move onto the process of \text{dp}dp array formation. We make use of \text{dp}dp array of size n+1n+1,
     * where nn is the length of the given string. We also use two index pointers ii and jj, where ii refers to the length of the substring (s's
     * ′
     * ) considered currently starting from the beginning, and jj refers to the index partitioning the current substring (s's
     * ′
     * ) into smaller substrings s'(0,j)s
     * ′
     * (0,j) and s'(j+1,i)s
     * ′
     * (j+1,i). To fill in the \text{dp}dp array, we initialize the element \text{dp}[0]dp[0] as \text{true}true,
     * since the null string is always present in the dictionary, and the rest of the elements of \text{dp}dp as \text{false}false.
     * We consider substrings of all possible lengths starting from the beginning by making use of index ii. For every such substring, we partition the string into two further substrings s1's1
     * ′
     * and s2's2
     * ′
     * in all possible ways using the index jj (Note that the ii now refers to the ending index of s2's2
     * ′
     * ). Now, to fill in the entry \text{dp}[i]dp[i], we check if the \text{dp}[j]dp[j] contains \text{true}true, i.e. if the substring s1's1
     * ′
     * fulfills the required criteria. If so, we further check if s2's2
     * ′
     * is present in the dictionary. If both the strings fulfill the criteria, we make \text{dp}[i]dp[i] as \text{true}true, otherwise as \text{false}false.
     */
    //O(N3)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak1(String s, List<String> dict1) {
        Set<String> dict = new HashSet<>(dict1);
        if (dict.contains(s)) return true;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        // use a set to record checked index to avoid repeated work.
        // This is the key to reduce the running time to O(N^2).
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            for (int i = curIdx + 1; i <= s.length(); i++) {
                if (visited.contains(i)) continue;
                if (dict.contains(s.substring(curIdx, i))) {
                    if (i == s.length()) return true;
                    queue.offer(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }
}
