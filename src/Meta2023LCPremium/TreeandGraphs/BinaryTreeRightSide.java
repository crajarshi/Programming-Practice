//package Meta2023LCPremium.TreeandGraphs;
//
//import Facebook.TreeNode;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Raj on 6/23/19.
// */
//public class BinaryTreeRightSide {
//
//    /**
//     * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//     *
//     *
//     *
//     * Example 1:
//     *
//     *
//     * Input: root = [1,2,3,null,5,null,4]
//     * Output: [1,3,4]
//     * Example 2:
//     *
//     * Input: root = [1,null,3]
//     * Output: [1,3]
//     * Example 3:
//     *
//     * Input: root = []
//     * Output: []
//     *
//     * The core idea of this algorithm:
//     * <p>
//     * 1.Each depth of the tree only select one node.
//     * 2. View depth is current size of result list.
//     * (1) the traverse of the tree is NOT standard pre-order traverse. It checks the RIGHT node first and then the LEFT
//     * (2) the line to check currDepth == result.size() makes sure the first element of that level will be added to the result list
//     * (3) if reverse the visit order, that is first LEFT and then RIGHT, it will return the left view of the tree.
//     *
//     * @param root
//     * @return
//     */
////O(N) Both
//    public List<Integer> rightSideView(TreeNode root) {
//        List<Integer> result = new ArrayList<Integer>();
//        rightView(root, result, 0);
//        return result;
//    }
//
//    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
//        if (curr == null) {
//            return;
//        }
//        if (currDepth == result.size()) {
//            result.add(curr.val);
//        }
//
//        rightView(curr.right, result, currDepth + 1);
//        rightView(curr.left, result, currDepth + 1);
//
//    }
//}
