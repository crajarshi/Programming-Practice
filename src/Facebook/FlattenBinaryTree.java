package Facebook;


/**
 * Created by Raj on 6/23/19.
 */
public class FlattenBinaryTree {

    private static TreeNode prev = null;

    public static void flatten(TreeNode root) {
        if (root == null)
            return;
        if (root.right != null) flatten(root.right);
        if (root.left != null) flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void main(String[] args) {

        TreeNode treeNode = printTreeNode();
        flatten(treeNode);

    }

    public static TreeNode printTreeNode() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(5);

        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.right = new TreeNode(6);

        while (treeNode != null) {
            System.out.println(treeNode.val);
            treeNode = treeNode.right;
        }
        return treeNode;
    }
}
