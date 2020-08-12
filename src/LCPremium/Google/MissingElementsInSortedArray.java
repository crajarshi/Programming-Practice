package LCPremium.Google;

/**
 * Given a sorted array A of unique numbers, find the K-th missing number
 * starting from the leftmost number of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [4,7,9,10], K = 1 Output: 5 Explanation: The first missing number
 * is 5. Example 2:
 * <p>
 * Input: A = [4,7,9,10], K = 3 Output: 8 Explanation: The missing numbers are
 * [5,6,8,...], hence the third missing number is 8. Example 3:
 * <p>
 * Input: A = [1,2,4], K = 3 Output: 6 Explanation: The missing numbers are
 * [3,5,6,7,...], hence the third missing number is 6.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 50000 1 <= A[i] <= 1e7 1 <= K <= 1e8
 */
public class MissingElementsInSortedArray {
    /**
     * Let missingNum be amount of missing number in the array. Two cases that
     * need to be handled:
     * <p>
     * missingNum < k, then return nums[n - 1] + k - missingNum missingNum >= k,
     * then use binary search(during the search k will be updated) to find the
     * index in the array, where the kth missing number will be located in
     * (nums[index], nums[index + 1]), return nums[index] + k
     *
     * @param nums
     * @param k
     * @return
     */
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int h = n - 1;
        int missingNum = nums[n - 1] - nums[0] + 1 - n;
        //nums[n - 1] - nums[0] + 1: the total number from beginning to ending, e.g.[4,7,9,10], if filled with all numbers [4,5,6,7,8,9,10] totally 7
        // nums[n - 1] - nums[0] + 1 - n: this array missing how many numbers,
        //     e.g. should be 7 numbers but only 4, missing 3

        if (missingNum < k) {
            return nums[n - 1] + k - missingNum;
        }

        while (l < h - 1) {
            int m = l + (h - l) / 2;
            int missing = nums[m] - nums[l] - (m - l);

            if (missing >= k) {
                // when the number is larger than k, then the index won't be located in (m, h]
                h = m;
            } else {
                // when the number is smaller than k, then the index won't be located in [l, m), update k -= missing
                k -= missing;
                l = m;
            }
        }

        return nums[l] + k;
    }

    public int missingElement2(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int missing = nums[right] - nums[left] - (right - left);

        if (k > missing) {
            return nums[right] + k - missing;
        }

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            missing = nums[mid] - nums[left] - (mid - left);

            if (k > missing) {
                k -= missing;
                left = mid;
            } else {
                right = mid;
            }
        }

        return nums[left] + k;
    }
}
