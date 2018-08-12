package leetcode;

/**
 * Any path can be written as two arrows (in different directions) from some node, where an arrow is a path that starts at some node and only travels down to child nodes.
 * <p>
 * If we knew the maximum length arrows L, R for each child, then the best path touches L + R + 1 nodes.
 * Let's calculate the depth of a node in the usual way: max(depth of node.left, depth of node.right) + 1.
 * While we do, a path "through" this node uses 1 + (depth of node.left) + (depth of node.right) nodes.
 * Let's search each node and remember the highest number of nodes used in some path
 * <p>
 * For every node, length of longest path which pass it = MaxDepth of its left subtree + MaxDepth of its right subtree.\
 * Time Complexity: O(N). We visit every node once.
 * <p>
 * Space Complexity: O(N), the size of our implicit call stack during our depth-first search.
 */
public class DiameterBinaryTree {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }
}
