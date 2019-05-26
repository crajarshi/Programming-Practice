package Linkedin;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers,
 * your task is to count the number of triplets chosen from the array that can make triangles
 * if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 */
public class ValidTriangle {
    public static int triangleNumber(int[] nums) {
        /*

     1. Remember, the array is sorted
2. If the sum of two small elements (at left index and right index) is greater
than the
rightmost element (largest), then it is obvious that the subsequent
sum of pairs will be greater than the rightmost element.
In your case [2,2,4,5], if (2+4 > 5) then keeping the
right index at the same position and increasing the left (the second 2+4 is obviously > 5)
3. The reason for count+=r-l is to find out how many pairs satisfy this property
In this case, there are two pairs that satisfy this property (2,4) and (2,4). since the right index is decremented, the count becomes r-l which is 2-0 (2 pairs).
I had the same doubt and I tried to work with different examples to understand.
         */
        Arrays.sort(nums);
        int count = 0, n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    count += r - l;
                    r--;
                } else l++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4};
        System.out.println(triangleNumber(nums));
    }
}
