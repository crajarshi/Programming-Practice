package leetcode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * <p>
 * Explanation :-
 * I use Stack to store directed left children from root.
 * When next() be called, I just pop one element and process its right child as new root.
 * The code is pretty straightforward.
 * So this can satisfy O(h) memory, hasNext() in O(1) time,
 * But next() is O(h) time.
 * <p>
 * So our first step is to point to pointer to the left most TreeNode. The problem is how to do back trace.
 * Since the TreeNode doesn't have father pointer,
 * we cannot get a TreeNode's father node in O(1) without store it beforehand.
 * Back to the first step, when we are traversal to the left most TreeNode,
 * we store each TreeNode we met ( They are all father nodes for back trace).
 * <p>
 * After that, I try an example, for next(), I directly return where the pointer pointing at,
 * which should be the left most TreeNode I previously found.
 * What to do next? After returning the smallest TreeNode,
 * I need to point the pointer to the next smallest TreeNode.
 * When the current TreeNode has a right branch
 * (It cannot have left branch, remember we traversal to the left most),
 * we need to jump to its right child first and then traversal to its right child's left most TreeNode.
 * When the current TreeNode doesn't have a right branch,
 * it means there cannot be a node with value smaller than itself father node, point the pointer at its father node
 */
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }

    private void pushAll(TreeNode node) {
        for (; node != null; stack.push(node), node = node.left) ;
    }
}
