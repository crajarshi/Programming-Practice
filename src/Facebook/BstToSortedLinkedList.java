package Facebook;

/**
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.


 */
public class BstToSortedLinkedList {

    // the smallest (first) and the largest (last) TreeNodes
    TreeNode first = null;
    TreeNode last = null;

    public static void main(String[] args) {
//        TreeTreeNode treeTreeNode = new TreeTreeNode(1);
//        treeTreeNode.left = new TreeTreeNode(2);
//        treeTreeNode.right = new TreeTreeNode(5);
//
//        treeTreeNode.left.left = new TreeTreeNode(3);
//        treeTreeNode.left.right = new TreeTreeNode(4);
//        treeTreeNode.right.right = new TreeTreeNode(6);

    }

    public void helper(TreeNode TreeNode) {
        if (TreeNode != null) {
            // left
            helper(TreeNode.left);
            // TreeNode
            if (last != null) {
                // link the previous TreeNode (last)
                // with the current one (TreeNode)
                last.right = TreeNode;
                TreeNode.left = last;
            } else {
                // keep the smallest TreeNode
                // to close DLL later on
                first = TreeNode;
            }
            last = TreeNode;
            // right
            helper(TreeNode.right);
        }
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }

}
