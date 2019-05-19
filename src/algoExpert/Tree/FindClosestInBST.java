package algoExpert.Tree;

/**
 * Created by Raj on 5/18/19.
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
