package Linkedin;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1.
 * Example 2:
 * <p>
 * Input: [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */

public class NestedListWeightSum {

    /*
    Let's keep nodes of each tree level in the queue structure, which

    typically orders elements in a FIFO (first-in-first-out) manner.
    In Java one could use LinkedList implementation of the Queue interface.
    The zero level contains only one node root. The algorithm is simple :
    Initiate queue with a root and start from the level number 0 : level = 0.
    While queue is not empty :
    Start the current level by adding an empty list into output structure levels.

    Compute how many elements should be on the current level : it's a queue length.

    Pop out all these elements from the queue and add them into the current level.

    Push their child nodes into the queue for the next level.

    Go to the next level level++.

         */
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return 0;
        }

        int sum = 0;
        int level = 1;

        Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();

                if (ni.isInteger()) {
                    sum += ni.getInteger() * level;
                } else {
                    queue.addAll(ni.getList());
                }
            }

            level++;
        }

        return sum;
    }

    public int depthSum2(List<NestedInteger> nestedList) {
        int level = 1, total = 0;
        while (nestedList.size() != 0) {
            List<NestedInteger> next = new LinkedList<>();
            for (NestedInteger nInt : nestedList) {
                if (nInt.isInteger())
                    total += nInt.getInteger() * level;
                else
                    next.addAll(nInt.getList());
            }
            level++;
            nestedList = next;
        }
        return total;
    }


    public interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }
}
