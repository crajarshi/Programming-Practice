package Linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves,
 * repeat until the tree is empty.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3,4,5]
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * Output: [[4,5,3],[2],[1]]
 * <p>
 * <p>
 * Explanation:
 * <p>
 * 1. Removing the leaves [4,5,3] would result in this tree:
 * <p>
 * 1
 * /
 * 2
 * <p>
 * <p>
 * 2. Now removing the leaf [2] would result in this tree:
 * <p>
 * 1
 * <p>
 * <p>
 * 3. Now removing the leaf [1] would result in the empty tree:
 * <p>
 * []
 */

public class FindLeavesofBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode node, List<List<Integer>> res) {
        if (null == node) return -1;
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if (res.size() < level + 1) res.add(new ArrayList<>());
        res.get(level).add(node.val);
        return level;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
