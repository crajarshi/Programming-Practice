package Meta2023LCPremium;


import Facebook.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.

 For example, given the following tree:

 1
 / \
 2   5
 / \   \
 3   4   6
 The flattened tree should look like:

 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6
 */
public class FlattenBinaryTree {

    private static TreeNode prev = null;

    /**
     * If we traverse the flattened tree in the reverse way, we would notice that [6->5->4->3->2->1]
     * is in (right, left, root) order of the original tree.
     * So the reverse order after flattening is post order traversal in (right, left, root) order like [6->5->4->3->2->1].
     * and then set each node's right pointer as the previous one in [6->5->4->3->2->1],
     * as such the right pointer behaves similar to a link in the flattened tree(though technically,
     * it's still a right child reference from the tree data structure's perspective) and set the left child as null before the end of one recursion by
     */
    public static void flatten(TreeNode root) {//Post Order traversal
        if (root == null)
            return;
        if (root.right != null) flatten(root.right);
        if (root.left != null) flatten(root.left);
        root.right = prev;//
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

//        while (treeNode != null) {
//            System.out.println(treeNode.val);
//            treeNode = treeNode.right;
//        }
        return treeNode;
    }
}
