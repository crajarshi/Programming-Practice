package Meta2023LCPremium.TreeandGraphs;

import java.util.*;

/**
 * Given the root of a binary tree,
 * return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Example 2:
 *
 *
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Example 3:
 *
 *
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeVerticalOrderTraversal {
    /**
     * Definition for a binary tree node.
     public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
     this.val = val;
     this.left = left;
     this.right = right;
     }
     * }
     */


    /**
     * Overview
     *
     * This is yet another problem about Binary Tree traversals.
     * As one would probably know, the common strategies to traverse a Tree data structure are
     * Breadth-First Search (a.k.a BFS) and Depth-First Search (a.k.a. DFS).
     *
     * The DFS strategy can be further distinguished as preorder DFS, inorder DFS and postorder DFS,
     * depending on the relative order of visit among the node itself and its child nodes.
     *
     * In the problem description, we are asked to return the vertical order of a binary tree,
     * which actually implies two sub-orders,
     * column-wise order
     * If we look at a binary tree horizontally,
     * each node can be aligned to a specific column,
     * based on its relative offset to the root node of the tree.
     *
     *
     * Let us assume that the root node has a column index of 0,
     * then its left child node would have a column index of -1 and
     * its right child node would have a column index of +1, and so on.
     *
     *
     * row-wise order
     *
     *
     * Now if we put the nodes into a vertical dimension,
     * each node would be assigned to a specific row,
     * based on its level (i.e. the vertical distance to the root node).
     *
     *
     * Let us assume that the root node has a row index of 0,
     * then both its child nodes would have the row index of 1.
     *
     * Given the above definitions,
     * we can now formulate the problem as a task to order the nodes based
     * on the 2-dimensional coordinates that we defined above.
     *
     * More specifically,
     * the nodes should be ordered by column first,
     * and further the nodes on the same column should be ordered vertically based on their row indices.
     *
     * With the formulation of the problem in the overview section,
     *
     * one of the most intuitive solutions to tackle the problem would be applying the BFS traversal,
     * where the nodes would be visited level by level.
     *
     * With the BFS traversal, we naturally can guarantee the vertical order of the visits,
     * i.e. the nodes at higher levels (large row values)
     * would get visited later than the ones at lower levels.
     *
     * However, we are still missing the horizontal order ( the column order).
     * To ensure this order, we need to do some additional processing during the BFS traversal.
     *
     * The idea is that we keep a hash table (let's denote it as columnTable<key, value>),
     * where we keep the node values grouped by the column index.
     *
     * The key in the hash table would be the column index,
     * and the corresponding value would be a list which contains the values of all the nodes
     * that share the same column index.
     *
     * In addition, the values in the corresponding list should be ordered by their row indices,
     * which would be guaranteed by the BFS traversal as we mentioned before.
     *
     * Algorithm
     *
     * We elaborate on the steps to implement the above idea.
     *
     * First, we create a hash table named columnTable to keep track of the results.

     * As to the BFS traversal, a common code pattern would be to use a queue data structure
     * to keep track of the order we need to visit nodes.
     * We initialize the queue by putting the root node along with its column index value (0).

     * We then run the BFS traversal with a loop consuming the elements from the queue.
     *
     *
     * At each iteration within the BFS, we pop out an element from the queue.
     * The element consists of a node and its corresponding column index.
     * If the node is not empty, we then populate the columnTable with the value of the node.
     * Subsequently, we then put its child nodes along
     * with their respective column indices (i.e. column-1 and column+1) into the queue.
     *
     *
     * At the end of the BFS traversal, we obtain a hash table that contains the desired node values
     * grouped by their column indices.
     * For each group of values, they are further ordered by their row indices.
     *
     *
     * We then sort the hash table by its keys, i.e. column index in ascending order.
     * And finally we return the results column by column.
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * The following solution takes 5ms.
     *
     * BFS, put node, col into queue at the same time
     * Every left child access col - 1 while right child col + 1
     * This maps node into different col buckets
     * Get col boundary min and max on the fly
     * Retrieve result from cols
     * Note that TreeMap version takes 9ms.
     * Time Complexity: O(N) N is the number of nodes in the tree.
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);
        cols.add(0);

        int min = 0;
        int max = 0;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);

            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }

            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
