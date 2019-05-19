package algoExpert.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Raj on 5/19/19.
 */
public class MaxPathSum {
    // O(n) time | O(log(n)) space
    public static Integer maxPathSum(BinaryTree tree) {
        ArrayList<Integer> maxSumArray = findMaxSum(tree);
        return maxSumArray.get(1);
    }

    public static ArrayList<Integer> findMaxSum(BinaryTree tree) {
        if (tree == null) {
            return new ArrayList<Integer>(Arrays.asList(0, 0));
        }
        ArrayList<Integer> leftMaxSumArray = findMaxSum(tree.left);
        Integer leftMaxSumAsBranch = leftMaxSumArray.get(0);
        Integer leftMaxPathSum = leftMaxSumArray.get(1);

        ArrayList<Integer> rightMaxSumArray = findMaxSum(tree.right);
        Integer rightMaxSumAsBranch = rightMaxSumArray.get(0);
        Integer rightMaxPathSum = rightMaxSumArray.get(1);

        Integer maxChildSumAsBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch);
        Integer maxSumAsBranch = Math.max(maxChildSumAsBranch + tree.value, tree.value);
        Integer maxSumAsRootNode = Math.max(
                leftMaxSumAsBranch + tree.value + rightMaxSumAsBranch,
                maxSumAsBranch
        );
        int maxPathSum = Math.max(leftMaxPathSum, Math.max(rightMaxPathSum, maxSumAsRootNode));

        return new ArrayList<Integer>(Arrays.asList(maxSumAsBranch, maxPathSum));
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }


}
