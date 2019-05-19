package algoExpert.Search;

/**
 * Created by Raj on 5/19/19.
 */
public class BinarySearch {

    // O(log(n)) time | O(1) space
    public static int binarySearch(int[] array, int target) {
        return binarySearch(array, target, 0, array.length - 1);
    }

    public static int binarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int middle = (left + right) / 2;
            int potentialMatch = array[middle];
            if (target == potentialMatch) {
                return middle;
            } else if (target < potentialMatch) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    // O(log(n)) time | O(log(n)) space
    public static int binarySearchRecurse(int[] array, int target) {
        return binarySearchRecurse(array, target, 0, array.length - 1);
    }

    public static int binarySearchRecurse(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        int potentialMatch = array[middle];
        if (target == potentialMatch) {
            return middle;
        } else if (target < potentialMatch) {
            return binarySearchRecurse(array, target, left, middle - 1);
        } else {
            return binarySearchRecurse(array, target, middle + 1, right);
        }
    }
}
