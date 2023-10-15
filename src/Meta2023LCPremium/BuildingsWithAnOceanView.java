package Meta2023LCPremium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * There are n buildings in a line. You are given an integer array heights of size n
 * that represents the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings. A building has an ocean view
 * if the building can see the ocean without obstructions.
 * Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 * Example 2:
 *
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 * Example 3:
 *
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 109
 */
public class BuildingsWithAnOceanView {

    /**
     * Algorithm
     *
     * Initialize maxHeight to -1. It will store the maximum height of the buildings to the right of the current building.
     * Iterate over the buildings array from right to left.
     * If the current building is taller than maxHeight, then append its index to the answer array
     * and update maxHeight with the current building's height.
     * At the end, the answer array has the indices of the buildings that can see the ocean in descending order.
     * Reverse the answer array (to make it in ascending order) and return it.
     * @param heights
     * @return
     */
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        int maxHeight = -1;

        for (int current = n - 1; current >= 0; --current) {
            // If there is no building higher (or equal) than the current one to its right,
            // push it in the list.
            if (maxHeight < heights[current]) {
                list.add(current);

                // Update max building till now.
                maxHeight = heights[current];
            }
        }
        int[] answer = new int[list.size()];
        Collections.reverse(list);
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
