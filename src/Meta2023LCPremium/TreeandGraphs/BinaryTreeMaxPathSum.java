package Meta2023LCPremium.TreeandGraphs;

import Facebook.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Output: 6
 * Example 2:
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * Output: 42
 */
public class BinaryTreeMaxPathSum {
    /**
     * A path from start to end, goes up on the tree for 0 or more steps,
     * then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node,
     * which is also the lowest common ancestor of all other nodes on the path.
     * A recursive method maxPathDown(TreeNode node) (1) computes the maximum path sum
     * with highest node is the input node, update maximum if necessary (2) returns the maximum sum of the path that can be extended to input node's parent.
     *
     * TC - O(N)
     */
    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    public int maxPathSumWithoutGlobal(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSum(max, root);
        return max[0];
    }

    private int maxPathSum(int[] max, TreeNode root) {
        if (root == null)
            return 0;
        int leftMax = Math.max(0, maxPathSum(max, root.left));
        int rightMax = Math.max(0, maxPathSum(max, root.right));
        max[0] = Math.max(max[0], root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}
