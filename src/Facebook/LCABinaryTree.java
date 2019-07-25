package Facebook;

/**
 * Created by Raj on 7/24/19.
 */
public class LCABinaryTree {
    //O(N) both
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }
}
