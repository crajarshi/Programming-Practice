package LCPremium.Medium;

/**
 * Given a binary search tree and a node in it, find the in-order successor of
 * that node in the BST.
 * <p>
 * The successor of a node p is the node with the smallest key greater than
 * p.val. Note:
 * <p>
 * If the given node has no in-order successor in the tree, return null. It's
 * guaranteed that the values of the tree are unique.
 * <p>
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class InorderSuccessor {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val > p.val) {
                res = root;
                root = root.left;
            } else root = root.right;
        }
        return res;
    }

    /*
    The idea is to compare root's value with p's value if root is not null,
    and consider the following two cases:

root.val > p.val. In this case, root can be a possible answer,
so we store the root node first and call it res. However, we don't know
if there is anymore node on root's left that is larger than p.val.
So we move root to its left and check again.

root.val <= p.val. In this case, root cannot be p's inorder successor,
neither can root's left child. So we only need to consider root's right child,
thus we move root to its right and check again.

We continuously move root until exhausted. To this point, we only need to return the res in case 1.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
