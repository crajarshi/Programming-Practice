package LCPremium.Google;

import java.util.Arrays;

/**
 * You have one chocolate bar that consists of some chunks. Each chunk has its
 * own sweetness given by the array sweetness.
 * <p>
 * You want to share the chocolate with your K friends so you start cutting the
 * chocolate bar into K+1 pieces using K cuts, each piece consists of some
 * consecutive chunks.
 * <p>
 * Being generous, you will eat the piece with the minimum total sweetness and
 * give the other pieces to your friends.
 * <p>
 * Find the maximum total sweetness of the piece you can get by cutting the
 * chocolate bar optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5 Output: 6 Explanation: You can
 * divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9] Example 2:
 * <p>
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8 Output: 1 Explanation: There is
 * only one way to cut the bar into 9 pieces. Example 3:
 * <p>
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2 Output: 5 Explanation: You can
 * divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= K < sweetness.length <= 10^4 1 <= sweetness[i] <= 10^5
 * <p>
 * More Good Binary Search Problems Here are some similar binary search
 * problems. Also find more explanations. Good luck and have fun.
 * <p>
 * 1231. Divide Chocolate 1011Capacity To Ship Packages In N Days 875 Koko
 * Eating Bananas 774 Minimize Max Distance to Gas Station 410 Split Array
 * Largest Sum
 */

public class DivideChocolate {

    /**
     * Explanation We want to maximize the minimum sweetness. Binary search the
     * result between 1 and 10^9.
     * <p>
     * Don'e explain binary search too much again and again. Please find more
     * related explanation in More. Also will explain it more in details on
     * youtube lee215.
     * <p>
     * Time O(Nlog(10^9)) Space O(1)
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * A good contrast explanation:
     * <p>
     * In this problem we want to find: Maximum of minimum total sweetness In
     * "Split Array Largest sum" we want to find: Minimum of maximum largest
     * sum
     * <p>
     * In both places we do binary search on target answer, the difference is
     * subtle but the key:
     * <p>
     * In this when we overshoot the target, we will include that number in
     * previous sum, as that is how we will maintain the target as the minimum
     * number and binary search will find this optimal minimum In "Split Array
     * Largest sum" when we overshoot the target, we will include the number in
     * the next sum, so we can ensure all numbers are less than target - binary
     * search does the rest of the magic Regarding people's questions on why the
     * binary search target is guaranteed to be the answer is because if there
     * is a better optimal answer binary search will keep heading to it, can
     * prove by contradiction
     */


    public int maximizeSweetness(int[] A, int K) {
        int left = 1, right = (int) 1e9 / (K + 1);
        while (left < right) {
            int mid = (left + right + 1) / 2;
            int cur = 0, cuts = 0;
            for (int a : A) {
                if ((cur += a) >= mid) {
                    cur = 0;
                    if (++cuts > K) break;
                }
            }
            if (cuts > K)
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    /**
     * Use 0 and the sum(sweetness) / (K + 1) as left and right boundaries of
     * binary search; Use the middle value to check how many pieces we have, and
     * compare with K + 1 to decide to search left or right half in next round.
     * Note: Because we would rather have more pieces than K + 1, so we choose
     * the tendency to right boundary during binary search.
     */

    public int maximizeSweetness2(int[] sweetness, int K) {
        int l = 0, r = Arrays.stream(sweetness).sum() / (K + 1);
        while (l < r) {
            int mid = l + (r - l + 1) / 2, cnt = 0;
            for (int i = 0, cur = 0; i < sweetness.length; ++i) {
                cur += sweetness[i];
                if (cur >= mid) {
                    cur = 0;
                    ++cnt;
                }
            }
            if (cnt >= K + 1) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
