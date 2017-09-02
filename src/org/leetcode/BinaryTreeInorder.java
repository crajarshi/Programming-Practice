package org.leetcode;

import org.epi.binarytree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raj on 9/2/17.
 */
public class BinaryTreeInorder {

    public static List < Integer > inorderMorrisTraversal(BinaryTreeNode<Integer> root) {
        List < Integer > res = new ArrayList< >();
        BinaryTreeNode<Integer> curr = root;
        BinaryTreeNode<Integer> pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.data);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                BinaryTreeNode<Integer> temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /* Let us create following Tree
              3
           /     \
          4      12
         /  \    /  \
       1    40  51   70 */
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<>();
        tree.data = 3;
        tree.left = new BinaryTreeNode<Integer>();
        tree.left.data = 4;
        tree.left.left = new BinaryTreeNode<Integer>();
        tree.left.left.data = 1;
        tree.left.right = new BinaryTreeNode<Integer>();
        tree.left.right.data = 40;
        tree.right = new BinaryTreeNode<Integer>();
        tree.right.data = 12;
        tree.right.left = new BinaryTreeNode<Integer>();
        tree.right.left.data = 51;
        tree.right.right = new BinaryTreeNode<Integer>();
        tree.right.right.data = 70;

        System.out.println(inorderMorrisTraversal(tree));
    }
}
