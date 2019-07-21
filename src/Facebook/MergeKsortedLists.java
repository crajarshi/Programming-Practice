package Facebook;

import Facebook.AddTwoNumbers.ListNode;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKsortedLists {
    //TC O(N)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    // O(NLOGK)

    /**
     * The mergeTwoLists functiony in my code comes from the problem Merge Two Sorted Lists whose complexity obviously is O(n), n is the sum of length of l1 and l2.
     * <p>
     * To put it simpler, assume the k is 2^x, So the progress of combination is like a full binary tree, from bottom to top.
     * So on every level of tree, the combination complexity is n, beacause every level have all n numbers without repetition.
     * The level of tree is x, ie logk. So the complexity is O(nlogk).
     * <p>
     * for example, 8 ListNode, and the length of every ListNode is x1, x2,
     * x3, x4, x5, x6, x7, x8, total is n.
     * <p>
     * on level 3: x1+x2, x3+x4, x5+x6, x7+x8 sum: n
     * <p>
     * on level 2: x1+x2+x3+x4, x5+x6+x7+x8 sum: n
     * <p>
     * on level 1: x1+x2+x3+x4+x5+x6+x7+x8 sum: n
     * total 3n, nlog8
     * <p>
     * This isn't an online solution.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        if (lists.size() == 2) return mergeTwoLists(lists.get(0), lists.get(1));
        return mergeTwoLists(mergeKLists(lists.subList(0, lists.size() / 2)),
                mergeKLists(lists.subList(lists.size() / 2, lists.size())));
    }


    /**
     * for (ListNode node:lists)
     * if (node!=null)
     * queue.add(node);
     * <p>
     * In this for loop he just added top node of each list, and that will be sorted since he has defined comparator.
     * And now in the while loop, he is traversing through the k lists and adding remaining item to queue to get them sorted.
     * Yes, queue size will be always list.size() or less (i.e if 3 lists were sent as parameter then queue will be holding 3 nodes after first for loop) .
     * But queue size will never be more than K , because we poll a node before we add another and we just add one node during each iteration.
     * Ex:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * <p>
     * After first FOR loop, queue will contain 1,1,2.
     * <p>
     * Now in the while loop, 1 (1st list) will be removed from queue and we test 1.next!=null and add 4 to the queue. so after 1st iteration queue will be 1,2,4.
     * <p>
     * In the 2nd iteration, 1 ( 2nd list) will be removed from queue and test 1.next!=null (1.next is 3), add 3 to the queue.
     * So queue will be 2,3,4 after this iteration and this continues, so the size of queue will always be <=K.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

}
