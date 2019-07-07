package algoExpert.Recursion;

import java.util.ArrayList;

/**
 * Created by Raj on 7/6/19.
 */
public class Subsets {
    // O(n*2^n) time | O(n*2^n) space
    public static ArrayList<ArrayList<Integer>> powerset(ArrayList<Integer> array) {
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
        subsets.add(new ArrayList<Integer>());
        for (Integer ele : array) {
            int length = subsets.size();
            for (int i = 0; i < length; i++) {
                ArrayList<Integer> currentSubset = new ArrayList<Integer>(subsets.get(i));
                currentSubset.add(ele);
                subsets.add(currentSubset);
            }
        }
        return subsets;
    }
}
