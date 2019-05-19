package algoExpert.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *Write a function that takes in a Binary Tree and returns its max path sum.
 * A path is a collection of connected nodes where no node is connected to more than two other nodes;
 * a path sum is the sum of the values of the nodes in a particular path.
 * Each Binary Tree node has a value stored in a property called "value" and
 * two children nodes stored in properties called "left" and "right," respectively.
 * Children nodes can either be Binary Tree nodes themselves or the None (null) value.

 Sample input:
 1
 /     \
 2        3
 /    \    /     \
 4      5 6       7

 Sample output: 18

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
