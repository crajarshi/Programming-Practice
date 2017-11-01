package com.epi.practice.ch5.Array;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Raj on 10/8/17.
 */
public class lengthOfLongestSubstring {
    /**
     * Finding length of the longest Substring
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = max(map.get(s.charAt(j)), i);
            }
            ans = max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * House Robbery/ Finding maximum sum in an array of Integers without selecting adjacent elements.
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        int temp = 0;
        for (int x : nums) {
            temp = currMax;
            currMax = max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    /**
     * Finding top k most frequent elements in an Array
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    /**
     * Check if an integer is a power of the given radix value.
     *
     * @param n
     * @param radix
     * @return
     */
    public static boolean isPowerOfRadix(int n, int radix) {
        return Integer.toString(n, radix).matches("10*");
    }

    /**
     * INORDER ITERATIVE.
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode node = stack.pop();
                result.add(node.val);  // Add after all left children
                p = node.right;
            }
        }
        return result;
    }

    /**
     * POSTORDER ITERATIVE
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.addFirst(p.val);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                p = node.left;           // Reverse the process of preorder
            }
        }
        return result;
    }

    /**
     * PREORDER ITERATIVE
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.add(p.val);  // Add before going to children
                p = p.left;
            } else {
                TreeNode node = stack.pop();
                p = node.right;
            }
        }
        return result;
    }

    public static int maxProduct(int[] nums) {
        // store the result that is the max we have found so far
        int r = nums[0];

        // imax/imin stores the max/min product of
        // subarray that ends with the current number nums[i]
        for (int i = 1, imax = r, imin = r; i < nums.length; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (nums[i] < 0)
                swap(imax, imin);

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = max(nums[i], imax * nums[i]);
            imin = min(nums[i], imin * nums[i]);

            // the newly computed max value is a candidate for our global result
            r = max(r, imax);

        }
        return r;
    }

    public static void swap(int i, int j) {
        int temp = i;
        j = i;
        i = temp;
    }

    /**
     * This Program calculates longest nonDecreasing Subsequence
     *
     * @param a
     * @return
     */
    public static int longestNonDecreasingSubSequence(List<Integer> a) {

        Integer[] maxLength = new Integer[(a.size())];
        Integer[] maxArr = new Integer[(a.size())];
        Map<Integer, List<Integer>> map = new HashMap<>(a.size());
        Arrays.fill(maxLength, 1);
        for (int i = 1; i < a.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (a.get(i) >= a.get(j))
                    maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
            }
        }
        System.out.println();
        return Collections.max(Arrays.asList(maxLength));
    }

    public static void main(String[] args) {

//        System.out.println(lengthOfLongestSubstring("abcdedefghijkgabc"));
//        System.out.println(lengthOfLongestSubstring("abcafg"));
//        System.out.println(lengthOfLongestSubstring("abcdaefgb"));

//        int nums[] = {2,3,-2,4};
//        System.out.println(maxProduct(nums));

//        int [] arr = {22,3,1,78,79,51,52};
//        System.out.println(rob(arr));

//        int [] arr1 = {3,3,10,78,79,51,52,76,3};
//        System.out.println(topKFrequent(arr1,2));

//        System.out.println(isPowerOfRadix(90,3));
//        System.out.println(isPowerOfRadix(64,4));

//        TreeNode treeNode = new TreeNode(1);
//         treeNode.left = new TreeNode(2);
//         treeNode.right = new TreeNode(3);
//         treeNode.left.left = new TreeNode(4);
//         treeNode.left.right = new TreeNode(5);
//         treeNode.right.left = new TreeNode(6);
//
////        System.out.println(inorderTraversal(treeNode));
////        System.out.println(preorderTraversal(treeNode));
//        System.out.println(postorderTraversal(treeNode));

        List<Integer> arr = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9);
        System.out.println(longestNonDecreasingSubSequence(arr));


    }

    //Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class Status {
        public int numTargetNodes;
        public TreeNode ancestor;
    }
}
