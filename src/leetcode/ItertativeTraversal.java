package leetcode;

import java.util.Stack;

/**
 * Created by Raj on 3/10/19.
 */
public class ItertativeTraversal {

    public static void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println(root);
                root = root.right;
            }
        }

    }


    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && root.val <= pre.val) return false;
                pre = root;
                root = root.right;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(9);
        node.left = new TreeNode(8);
        node.right = new TreeNode(10);

        System.out.println(isValidBST(node));
        inOrderTraversal(node);

    }
}
