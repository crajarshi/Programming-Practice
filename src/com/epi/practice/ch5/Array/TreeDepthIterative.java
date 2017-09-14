package com.epi.practice.ch5.Array;

import java.util.ArrayDeque;

/**
 * Created by Raj on 10/7/17.
 */
public class TreeDepthIterative {
    public int MaxDepth(TreeNode root) {
        TreeNode node = root;
        ArrayDeque<TreeNode> nodeStack = new ArrayDeque<>();
        ArrayDeque<Integer> depthStack = new ArrayDeque<>();

        int max = 0;
        int depth = 1;
        while (node != null || !nodeStack.isEmpty()) {
            if (node != null) {
                nodeStack.push(node);
                depthStack.push(depth);
                node = node.left;
                depth++;
            } else {
                node = nodeStack.pop();
                depth = depthStack.pop();

                if (depth > max) max = depth;

                node = node.right;
                depth++;
            }
        }

        return max;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
