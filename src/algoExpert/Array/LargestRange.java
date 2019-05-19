package algoExpert.Array;

import java.util.HashMap;

/**
 * Created by Raj on 5/18/19.
 */
public class LargestRange {
    // O(n) time | O(n) space
    public static int[] largestRange(int[] array) {
        int[] bestRange = new int[2];
        int longestLength = 0;
        HashMap<Integer, Boolean> nums = new HashMap<Integer, Boolean>();
        for (int num : array) {
            nums.put(num, true);
        }
        for (int num : array) {
            if (!nums.containsKey(num)) {
                continue;
            }
            nums.put(num, false);
            int currentLength = 1;
            int left = num - 1;
            int right = num + 1;
            while (nums.containsKey(left)) {
                nums.put(left, false);
                currentLength++;
                left--;
            }
            while (nums.containsKey(right)) {
                nums.put(right, false);
                currentLength++;
                right++;
            }
            if (currentLength > longestLength) {
                longestLength = currentLength;
                bestRange = new int[]{left + 1, right - 1};
            }
        }
        return bestRange;
    }
}
