package org.epi.binarytree;

/**
 * Created by Raj on 8/12/17.
 */

public class BinaryTreeExample {

    private static class BalanceStatusWithHeight {

        public boolean balanced;
        public int height;

        public BalanceStatusWithHeight(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public static boolean isBalnced(BinaryTreeNode<Integer> tree) {
        return checkBalanced(tree).balanced;
    }

    private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new BalanceStatusWithHeight(true, -1);
        }

        BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
        if (!leftResult.balanced) {

            return leftResult;
        }

        BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
        if (!rightResult.balanced) {

            return rightResult;
        }

        boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        return new BalanceStatusWithHeight(isBalanced, height);
    }

    public static void main(String[] args) {

        BinaryTreeNode<Integer> tree = new BinaryTreeNode<>();
        tree.left = new BinaryTreeNode<Integer>();
        tree.left.left = new BinaryTreeNode<Integer>();
        tree.right = new BinaryTreeNode<Integer>();
        tree.right.left = new BinaryTreeNode<Integer>();
        tree.right.right = new BinaryTreeNode<Integer>();

    }

}