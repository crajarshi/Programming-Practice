package leetcode;

import javafx.util.Pair;

import java.util.HashMap;

/**
 * Created by Raj on 9/16/18.
 */
public class SubArraySum {
    /**
     * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
     * <p>
     * Note:
     * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1, -1, 5, -2, 3], k = 3
     * Output: 4
     * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
     * Example 2:
     * <p>
     * Input: nums = [-2, -1, 2, 1], k = 1
     * Output: 2
     * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
     * <p>
     * <p>
     * The HashMap stores the sum of all elements before index i as key, and i a
     * s value. For each i, check not only t
     * he current sum but also (currentSum - previousSum)
     * to see if there is any that equals k, and update max length.
     * <p>
     * Let's say you've iterated to index 5 (randomly chosen)
     * and your sum from index 0 to 5 so far is 7, and k is 3. sum - k in this case is 4.
     * <p>
     * What map.containsKey(sum - k) returns is the index
     * where the sum of every element up to that index from index 0 is sum - k, or (7 - 4) == 3,
     * in our example. Let's say that that index returned by map.containsKey(sum - k)
     * is 2 (randomly chose one that is before index 5).
     * So knowing that at index 2 the total sum is 4, and at index 5,
     * the total sum is 7, that means the elements between index 2 and index 5 incremented the total sum by 3, or k!
     */
    public static int maxSubArrayLen(int[] nums, int target) {
        int sum = 0, max = 0, index = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            // if (sum == k) max = i + 1;
            if (map.containsKey(sum - target)) {
                index = map.get(sum - target);
                max = Math.max(max, i - index);
                System.out.println("The sum is between " + ((index - i) > 0 ? i + " and " + index + 1 : index + 1 + " and " + i));
            }

            if (!map.containsKey(sum)) map.put(sum, i);
        }
        // If we reach here, then no subarray exists
        System.out.println("No subarray with given sum exists");
        return max;
    }


    public static Pair<Integer, Integer> xSumSubArray(int[] a, int X) {
        int i = 0, j = 0, sum = a[0];
        while (i < a.length) {
            if (i > j) {
                j = i;
                sum = a[i];
            } else if (sum > X) {
                sum = sum - a[i++];
            } else if (sum < X) {
                if ((j + 1) < a.length)
                    sum = sum + a[++j];
                else
                    break;
            } else {
                return new Pair<>(i, j);
            }
        }
        return null;
    }

    /**
     * Given an unsorted array of integers, find a subarray which adds to a given number.
     * If there are more than one subarrays with sum as the given number, print any of them.
     */

//    public static void subArraySum(int arr[], int sum)
//    {
//        // create an empty map
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(0,-1);
//
//        // Maintains sum of elements so far
//        int curr_sum = 0;
//
//        for (int i = 0; i < arr.length; i++)
//        {
//            // add current element to curr_sum
//            curr_sum = curr_sum + arr[i];
//
//            // if curr_sum is equal to target sum
//            // we found a subarray starting from index 0
//            // and ending at index i
//            if (curr_sum == sum)
//            {
//                System.out.println( "Sum found between indexes "
//                        + 0 + " to " + i);
//                return;
//            }
//
//            // If curr_sum - sum already exists in map
//            // we have found a subarray with target sum
//            if (map.containsKey(curr_sum - sum))
//            {
//                System.out.println( "Sum found between indexes "
//                        + (map.get(curr_sum - sum)) + 1
//                        + " to " + i);
//                return;
//            }
//
//            map.put(curr_sum, i+1);
//        }
//
//        // If we reach here, then no subarray exists
//        System.out.println("No subarray with given sum exists");
//    }
    public static void main(String[] args) {

        int[] num = new int[]{1, -1, 5, -2, 3};
        int[] num1 = new int[]{1, 5, 6, 2, 3};
        int[] num2 = new int[]{1, -5, 6, 2, 3};
        int[] num3 = new int[]{1, 4, 20, 3, 10, 5};
        int[] num4 = new int[]{10, 2, -2, -20, 10};
        int[] num5 = new int[]{-10, 0, 2, -2, -20, 10};

//        System.out.println(maxSubArrayLen(num, 3));
//        System.out.println(xSumSubArray(num1,-4));
//        System.out.println(maxSubArrayLen(num3,33));
//       maxSubArrayLen(num4,-10);
        maxSubArrayLen(num5, 20);
    }
}
