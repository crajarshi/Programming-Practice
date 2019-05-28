package Linkedin;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree where the
 * original right nodes turned into left leaf nodes. Return the new root.
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
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 * <p>
 * 4
 * / \
 * 5   2
 * / \
 * 3   1
 * Clarification:
 * <p>
 * Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.
 * <p>
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 * <p>
 * Here's an example:
 * <p>
 * 1
 * / \
 * 2   3
 * /
 * 4
 * \
 * 5
 * The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
 */
public class BinaryTreeUpsideDown {
    /*
      https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure
     */
    public FindLeavesofBinaryTree.TreeNode upsideDownBinaryTree(FindLeavesofBinaryTree.TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        FindLeavesofBinaryTree.TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;   // node 2 left children
        root.left.right = root;         // node 2 right children
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
