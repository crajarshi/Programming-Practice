package Meta2023LCPremium.TreeandGraphs;

import Facebook.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia:
 “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

 Example 1:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of nodes 5 and 1 is 3.
 Example 2:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


 Note:

 All of the nodes' values will be unique.
 p and q are different and both values will exist in the binary tree.

 */
public class LCABinaryTree {
    //O(N) both

    /**
     * It's recursive and expands the meaning of the function.
     * If the current (sub)tree contains both p and q, then the function result is their LCA.
     * If only one of them is in that subtree,
     * then the result is that one of them. If neither are in that subtree, the result is null/None/nil.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            // found p or q or touch the ground
            return root;

        // search p and q from left and right
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            // from root's left & right we found both p and q, so root is the LCA
            return root;
        else
            // left is not null means from left's left & right we found both q and q
            // so left is the LCA, otherwise, right is the answer
            return left != null ? left : right;
    }
}
