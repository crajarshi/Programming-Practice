package algoExpert.Tree;

/**
 * You are given a BST data structure consisting of BST nodes.
 * Each BST node has an integer value stored in a property called "value" and
 * two children nodes stored in properties called "left" and "right," respectively.
 * A node is said to be a BST node if and only if it satisfies the BST property:
 * its value is strictly greater than the values of every node to its left;
 * its value is less than or equal to the values of every node to its right;
 * and both of its children nodes are either BST nodes themselves or None (null) values.
 * You are also given a target integer value;
 * write a function that finds the closest value to that target value contained in the BST.
 * Assume that there will only be one closest value.

 Sample input:
 10           , 12
 /       \
 5         15
 /    \     /      \
 2      5 13     22
 /                  \
 1                    14
 Sample output: 13

 */
public class FindClosestInBST {
    // Average: O(log(n)) time | O(log(n)) space
    // Worst: O(n) time | O(n) space
    public static int findClosestValueInBst(BST tree, int target) {
        return findClosestValueInBst(tree, target, Double.MAX_VALUE);
    }

    public static int findClosestValueInBst(BST tree, int target, double closest) {
        if (Math.abs(target - closest) > Math.abs(target - tree.value)) {
            closest = tree.value;
        }
        if (target < tree.value && tree.left != null) {
            return findClosestValueInBst(tree.left, target, closest);
        } else if (target > tree.value && tree.right != null) {
            return findClosestValueInBst(tree.right, target, closest);
        } else {
            return (int) closest;
        }
    }

    // Average: O(log(n)) time | O(1) space
    // Worst: O(n) time | O(1) space
    public static int findClosestValueInBstIterative(BST tree, int target) {
        return findClosestValueInBstIterative(tree, target, Double.MAX_VALUE);
    }

    public static int findClosestValueInBstIterative(BST tree, int target, double closest) {
        BST currentNode = tree;
        while (currentNode != null) {
            if (Math.abs(target - closest) > Math.abs(target - currentNode.value)) {
                closest = currentNode.value;
            }
            if (target < currentNode.value) {
                currentNode = currentNode.left;
            } else if (target > currentNode.value) {
                currentNode = currentNode.right;
            } else {
                break;
            }
        }
        return (int) closest;
    }


    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
