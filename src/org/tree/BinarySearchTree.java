package org.tree;

/**
 * Created by Raj on 7/15/17.
 */
// Java program to demonstrate insert operation in binary search tree
class BinarySearchTree {

    /* Class containing left and right child of current node and key value*/
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    void inorder() {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    // Function to find minimum value in a BST
    void findMin() {
        findMinRec(root);
    }

    void findMinRec(Node root) {
        if (root != null) {
            if (root.left != null)
                findMinRec(root.left);
            else
                System.out.println("Minimum element is:" + root.key);
        }
    }

    // Function to find height of a BST

    void findHeight() {System.out.println(" Height is:" +findHeightRec(root));}

    int findHeightRec(Node root){
        if (root == null) return -1;
        return Math.max(findHeightRec(root.left) ,findHeightRec(root.right)) + 1;
    }


    void treeTraversalCall() {
        treeTraversal(root);
    }

    void treeTraversal(Node root) {
        if (root != null) {
            // Preorder: Processes the root before the traversals of left and right
            // children.
            System.out.println("Preorder: " + root.key);
            treeTraversal(root.left);
            // Inorder: Processes the root after the traversal of left child and
            // before the traversal of right child.
            System.out.println("Inorder: " + root.key);
            treeTraversal(root.right);
            // Postorder: Processes the root after the traversals of left and right
            // children.
            System.out.println("Postorder: " + root.key);
        }
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
//        tree.inorder();
//        tree.findMin();
//        tree.findHeight();
        tree.treeTraversalCall();
    }
}
