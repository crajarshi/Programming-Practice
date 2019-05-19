package algoExpert.Search;

/**
 * Write a function that takes in a sorted array of integers as well as a target integer.
 * The caveat is that the numbers in the array have been shifted by some amount; in other words,
 * they have been moved to the left or the right by one or more positions.
 * For example, [1, 2, 3, 4] might become [3, 4, 1, 2].
 * The function should use a variation of the Binary Search algorithm to find if the target number is
 * contained in the array and should return its index if it is, otherwise -1.
 * <p>
 * Sample input: [45, 61, 71, 72, 73, 0, 1, 21, 33, 45], 33
 * Sample output: 8
 */
public class ShiftedBinarySearch {
    // O(log(n)) time | O(1) space
    public static int shiftedBinarySearch(int[] array, int target) {
        return shiftedBinarySearch(array, target, 0, array.length - 1);
    }

    public static int shiftedBinarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int middle = (left + right) / 2;
            int potentialMatch = array[middle];
            int leftNum = array[left];
            int rightNum = array[right];
            if (target == potentialMatch) {
                return middle;
            } else if (leftNum <= potentialMatch) {
                if (target < potentialMatch && target >= leftNum) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (target > potentialMatch && target <= rightNum) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }
}
