package algoExpert.DP;

/**
 * Write a function that takes in an array of positive integers and
 * returns an integer representing the maximum sum of non-adjacent elements in the array.
 * If a sum cannot be generated, the function should return 0.
 * <p>
 * Sample input: [75, 105, 120, 75, 90, 135]
 * Sample output: 330 (75, 120, 135)
 */
public class MaximumSubsetSumWithNoAdjacentElements {
    // O(n) time | O(1) space
    public static int maxSubsetSumNoAdjacent(int[] array) {
        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        }
        int second = array[0];
        int first = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            int current = Math.max(first, second + array[i]);
            second = first;
            first = current;
        }
        return first;
    }
}
