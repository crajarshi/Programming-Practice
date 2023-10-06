package Meta2023LCPremium;

import Facebook.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 */
public class DiameterOfBinaryTree {
    int max = 0;
    int[] max1 = new int[1];


    public int diameterOfBinaryTree(TreeNode root) {
        max1[0] = 0;
        maxDepth(root);
        maxDepth2(max1, root);
        return max1[0];
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }

    private int maxDepth2(int[] max2, TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth2(max2, root.left);
        int right = maxDepth2(max2, root.right);

        max2[0] = Math.max(max2[0], left + right);

        return Math.max(left, right) + 1;
    }

    /**
     * The idea is to use Post order traversal which means make sure the node is there till the left and right childs
     * are processed that's the reason you use peek method in the stack to not pop it off without being done with t
     * he left and right child nodes. Then for each node calculate the max of the left and right sub trees depth and
     * also simultaneously calculate the overall max of the left and right
     * subtrees count.
     */
    public int diameterOfBinaryTree3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int overallNodeMax = 0;
        Stack<TreeNode> nodeStack = new Stack<>();
        Map<TreeNode, Integer> nodePathCountMap = new HashMap<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.peek();
            if (node.left != null && !nodePathCountMap.containsKey(node.left)) {
                nodeStack.push(node.left);
            } else if (node.right != null && !nodePathCountMap.containsKey(node.right)) {
                nodeStack.push(node.right);
            } else {
                TreeNode rootNodeEndofPostOrder = nodeStack.pop();
                int leftMax = nodePathCountMap.getOrDefault(rootNodeEndofPostOrder.left, 0);
                int rightMax = nodePathCountMap.getOrDefault(rootNodeEndofPostOrder.right, 0);
                int nodeMax = 1 + Math.max(leftMax, rightMax);
                nodePathCountMap.put(rootNodeEndofPostOrder, nodeMax);
                overallNodeMax = Math.max(overallNodeMax, leftMax + rightMax);
            }

        }
        return overallNodeMax;

    }
}
