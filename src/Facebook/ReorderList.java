package Facebook;

import Facebook.AddTwoNumbers.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * Accepted
 * 163,138
 * Submissions
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // Find the middle node
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half ////Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode head2 = reverse(slow.next);
        slow.next = null;

        // Link the two halves together //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        while (head != null && head2 != null) {
            ListNode tmp1 = head.next;
            ListNode tmp2 = head2.next;
            head2.next = head.next;
            head.next = head2;
            head = tmp1;
            head2 = tmp2;
        }
    }

    private ListNode reverse(ListNode n) {
        ListNode prev = null;
        ListNode cur = n;
        while (cur != null) {
            ListNode tmp = cur.next;//save the next pointer
            cur.next = prev;//move the current pointer to prev
            prev = cur;//move prev by 1 to cur
            cur = tmp;//move cur by 1 to next
        }
        return prev;
    }
}
