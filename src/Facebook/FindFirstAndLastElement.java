package Facebook;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FindFirstAndLastElement {
    public static int[] searchRange(int[] nums, int target) {
        double left = target - 0.1, right = target + 0.1;
        int l = bs(nums, left), r = bs(nums, right);
        if (l == r) return new int[]{-1, -1};
        return new int[]{l, r - 1};
    }

    public static int bs(int[] nums, double target) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (target > nums[m]) l = m + 1;
            else h = m - 1;
        }
        return l;
    }
}

