package Facebook;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * Note:
 * <p>
 * next() and hasNext() should run in average O(1) time and
 * uses O(h) memory, where h is the height of the tree.
 * You may assume that next() call will always be valid, that is,
 * there will be at least a next smallest number in the BST when next() is called.
 */
public class BinarySearchTreeIterator {

    /**
     * Compare this typical iterative inorder traversal
     * <p>
     * 1.    TreeNode visit = root;
     * Stack<TreeNode> stack = new Stack();
     * 2.    while (visit != null || !stack.empty()) {
     * 3.        while (visit != null) {
     * stack.push(visit);
     * visit = visit.left;
     * }
     * TreeNode next = stack.pop();
     * visit = next.right;
     * doSomethingWith(next.val);
     * }
     * with what we're supposed to support here:
     * <p>
     * 1.    BSTIterator i = new BSTIterator(root);
     * 2.    while (i.hasNext())
     * 3.        doSomethingWith(i.next());
     * You can see they already have the exact same structure:
     * <p>
     * Some initialization.
     * A while-loop with a condition that tells whether there is more.
     * The loop body gets the next value and does something with it.
     * So simply put the three parts of that iterative solution into our three iterator methods:
     */
    private TreeNode visit;
    private Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        visit = root;
        stack = new Stack();
    }

    public boolean hasNext() {
        return visit != null || !stack.empty();
    }

    //This is in fact average O(1) time. The while loop is misleading you to think it is not.
// Think about the number of times a node has been visited after iterating the whole tree.
// Each node has been visited twice. In some cases the while loop doesn't execute,
// so that node at that call is only visited once. Where does the other visit go?
// It goes to the while loop when you visit another node. That's why it's "average" O(1) time
    public int next() {
        while (visit != null) {
            stack.push(visit);
            visit = visit.left;
        }
        TreeNode next = stack.pop();
        visit = next.right;
        return next.val;
    }
}

