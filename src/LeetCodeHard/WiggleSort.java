package LeetCodeHard;

import java.util.Arrays;

import static Facebook.KthLargestElement.findKthLargest;

/**
 * Created by Raj on 5/13/20.
 */
public class WiggleSort {
    public static void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int odd = 1;
        int even = nums.length % 2 == 0 ? nums.length - 2 : nums.length - 1;
        int[] tmpArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > median) {
                tmpArr[odd] = nums[i];
                odd += 2;
                continue;
            }
            if (nums[i] < median) {
                tmpArr[even] = nums[i];
                even -= 2;
                continue;
            }
        }
        while (odd < nums.length) {
            tmpArr[odd] = median;
            odd += 2;
        }
        while (even >= 0) {
            tmpArr[even] = median;
            even -= 2;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmpArr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 1, 1, 6, 4};
        wiggleSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    public void wiggleSort2(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        int n = nums.length;
        int left = (n + 1) / 2 - 1; // median index
        int right = n - 1; // largest value index
        for (int i = 0; i < nums.length; i++) {   // copy large values on odd indexes
            if (i % 2 == 1) {
                nums[i] = copy[right];
                right--;
            } else { // copy values decremeting from median on even indexes
                nums[i] = copy[left];
                left--;
            }
        }
    }
}
