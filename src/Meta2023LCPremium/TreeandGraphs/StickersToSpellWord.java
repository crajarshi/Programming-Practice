package Meta2023LCPremium.TreeandGraphs;

/**
 * We are given n different types of stickers. Each sticker has a lowercase English word on it.
 *
 * You would like to spell out the given string target by cutting
 * individual letters from your collection of stickers and rearranging them.
 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 *
 * Return the minimum number of stickers that you need to spell out target.
 * If the task is impossible, return -1.
 *
 * Note: In all test cases, all words were chosen randomly from the 1000 most common US English words,
 * and target was chosen as a concatenation of two random words.
 *
 *
 *
 * Example 1:
 *
 * Input: stickers = ["with","example","science"], target = "thehat"
 * Output: 3
 * Explanation:
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target string.
 * Example 2:
 *
 * Input: stickers = ["notice","possible"], target = "basicbasic"
 * Output: -1
 * Explanation:
 * We cannot form the target "basicbasic" from cutting letters from the given stickers.
 *
 *
 * Constraints:
 *
 * n == stickers.length
 * 1 <= n <= 50
 * 1 <= stickers[i].length <= 10
 * 1 <= target.length <= 15
 * stickers[i] and target consist of lowercase English letters.
 */

public class StickersToSpellWord {

    /**
     * Model the problem in terms of a graph.
     *
     * A string represents a vertex.
     * Define applying a sticker on a string as a new string having the union of characters
     * from both strings. With that definition, each vertex has stickers.length edges
     * corresponding to applying each sticker stickers[i] on current vertex string.
     * Our goal is to find the length of shortest path from
     * empty string "" vertex to target string vertex.
     * Once we see this modelling, BFS suits perfectly for our use case.
     *
     * Some notes with regards to my implementation below:
     *
     * I am finding the shortest path from target string to
     * empty string "" with the definition of applying a sticker reversed i.e.
     * removing the sticker characters from current string.
     * Optimization 1 (from Solution article):
     * we only need to concern ourselves with characters present in target string
     * so no need to keep all 26 characters.
     * Optimization 2 (from Solution article):
     * we can ignore all the dominated stickers from consideration. For example,
     * let's say a's and b's are required in target, it is always better to choose
     * sticker abb over sticker ab because either of them count as 1 only but choosing
     * former gives us 1 b extra. So we can say that sticker ab is dominated by some other
     * sticker (abb) and an optimal solution can always be found without using it.
     * Optimization 3: Only apply stickers (i.e. explore the edges)
     * which remove the first character from current string (vertex).
     * How does that still guarantee correctness? Reason: If stickers s1 -> s1 -> s2 -> s3 is
     * the shortest path to our destination then so is
     * s2 -> s1 -> s3 -> s1 or any other permutation of s1, s1, s2 and s3 since
     * order of applying stickers don't matter to the final result.
     * So we might be applying only selected stickers (exploring less edges) now but
     * first character might change for subsequent vertices and then we might
     * apply other stickers ensuring that we're moving towa
     */
}
