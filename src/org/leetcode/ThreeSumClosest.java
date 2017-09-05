package org.leetcode;

import java.util.Arrays;

/**
 * Created by Raj on 9/3/17.
 */
public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum - target);
                if (diff == 0) return sum;
                if (diff < min) {
                    min = diff;
                    result = sum;
                }
                if (sum <= target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {

        int[] nums = {-1, 2, 1, -4};
        int[] nums1 = {-14, 20, 11, -4};

        System.out.println(threeSumClosest(nums, 1));
        System.out.println(threeSumClosest(nums1, 13));


    }
}
