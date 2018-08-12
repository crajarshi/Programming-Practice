package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * <p>
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 * The maximum number of consecutive 1s is 3.
 * Note:
 * <p>
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 */
public class MaxConsecutiveOnes {
    /**
     * Given a binary array, find the maximum number of consecutive 1s in
     * this array if you can flip at most one 0.
     * <p>
     * Example 1:
     * Input: [1,0,1,1,0]
     * Output: 4
     * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
     * After flipping, the maximum number of consecutive 1s is 4.
     * Note:
     * <p>
     * The input array will only contain 0 and 1.
     * The length of input array is a positive integer and will not exceed 10,000
     * Follow up:
     * What if the input numbers come in one by one as an infinite stream?
     * In other words, you can't store all numbers coming from the stream as
     * it's too large to hold in memory. Could you solve it efficiently?
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnesSecond(int[] nums, int noOfZeroesToBeFlipped) {
        int max = 0, zero = 0; // flip at most noOfZeroesToBeFlipped zero
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zero++;
            while (zero > noOfZeroesToBeFlipped)
                if (nums[l++] == 0)
                    zero--;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnesSecond(new int[]{1, 0, 1, 1, 0}, 1));
        System.out.println(findMaxConsecutiveOnesSecond(new int[]{1, 1, 0, 1, 1, 1}, 0));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxHere = 0, max = 0;
        for (int n : nums)
            max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
        return max;
    }

    public int findMaxConsecutiveOnesQuueApproach(int[] nums) {
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>();
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}
