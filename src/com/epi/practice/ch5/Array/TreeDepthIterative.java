package com.epi.practice.ch5.Array;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raj on 10/7/17.
 */
public class TreeDepthIterative {
    public static String nextClosestTime(String time) {
        int cur = 60 * Integer.parseInt(time.substring(0, 2));
        cur += Integer.parseInt(time.substring(3));
        Set<Integer> allowed = new HashSet();
        for (char c : time.toCharArray())
            if (c != ':') {
                allowed.add(c - '0');
            }

        while (true) {
            cur = (cur + 1) % (24 * 60);
            int[] digits = new int[]{cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10};
            search:
            {
                for (int d : digits) if (!allowed.contains(d)) break search;
                return String.format("%02d:%02d", cur / 60, cur % 60);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("the time:" + nextClosestTime("19:34"));
    }

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
