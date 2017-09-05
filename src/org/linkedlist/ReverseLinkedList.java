package org.linkedlist;

import java.util.List;

/**
 * Created by Raj on 9/4/17.
 */
public class ReverseLinkedList {

    public static ListNode<Integer> reverseList(ListNode<Integer> listNode,int start,int finish){
        ListNode<Integer> dummyHead = new ListNode<>(0,listNode);
        ListNode<Integer> sublistHead = dummyHead;
        ListNode<Integer> sublistIter = sublistHead.next;
        while(start++ < finish){
            ListNode<Integer> temp = sublistIter.next;
            System.out.println("1:" +temp.data);
            sublistIter.next = temp.next;
            temp.next = sublistHead.next;
            System.out.println("2:" +temp.data);
            sublistHead.next = temp;
            System.out.println("3:" +sublistHead.next);

        }

         dummyHead = sublistHead;
        while(sublistHead.next != null){
            System.out.println("Node val is:" + sublistHead.data.toString());
            System.out.println("Next ptr is:" + sublistHead.next.toString());
            sublistHead= sublistHead.next;
        }
         return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode<Integer> L = new ListNode<>(1,
                new ListNode<>(2,
                        new ListNode<>(3,
                                new ListNode<>(4,
                                        new ListNode<>(5,
                                                new ListNode<>(6,
                                                        new ListNode<>(7, null)))))));
        ListNode<Integer> newL = L;

        while(L.next != null){
            System.out.println("Node val is:" + L.data.toString());
            System.out.println("Next ptr is:" + L.next.toString());
            L= L.next;
        }

        System.out.println(reverseList(newL,1,7));

    }
}
