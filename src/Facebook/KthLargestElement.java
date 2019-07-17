package Facebook;

import java.util.Random;

/**
 * Created by Raj on 7/16/19.
 */
public class KthLargestElement {

    // RC : O(N) AVG, O(N2) worst case || SC O(log N) AVG CASE O(N) worst
    // case -->Recursion stack
    public int findKthLargest(int[] nums, int k) {
        return selectionAlgorithm(nums, 0, nums.length - 1, nums.length -
                k /*target index*/); // For Kth Smallest just pass K -1 here
    }

    public int selectionAlgorithm(int[] a, int start, int end, int
            targetIndex) {
        int pivot = getRandom(start, end);
        int result = singlePlacementPartition(a, start, end, pivot);
        if (result > targetIndex) {
            return selectionAlgorithm(a, start, result - 1, targetIndex);
        } else if (result < targetIndex) {
            return selectionAlgorithm(a, result + 1, end, targetIndex);
        } else {
            return a[result];
        }
    }

    public int singlePlacementPartition(int[] a, int start, int end,
                                        int pivot) {
        swap(a, start, pivot);
        int less = start;
        for (int i = start + 1; i <= end; i++) {
            if (a[i] <= a[start]) swap(a, i, ++less);
        }
        swap(a, start, less);
        return less;
    }

    public int getRandom(int start, int end) {
        if (start > end) System.out.println();
        return new Random().nextInt(end - start + 1) + start;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;

    }
}
