package org.linkedlist;

import java.util.List;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InsertList {
    // @include
    // Insert newNode after node.
    public static void insertAfter(ListNode<Integer> node,
                                   ListNode<Integer> newNode) {
        newNode.next = node.next;
        node.next = newNode;
    }
    // @exclude

    private static void checkAnswer(ListNode<Integer> L, List<Integer> vals) {
        for (int i = 0; i < vals.size(); ++i) {
            assert (L.data == vals.get(i));
            L = L.next;
        }
        assert (L == null);
    }

    public static void main(String[] args) {
        ListNode<Integer> L;
        ListNode<Integer> newL = new ListNode<>(0,null);;
//        L = new ListNode<>(2, new ListNode<>(4, new ListNode<>(3, null)));
        L = new ListNode<>(1,
                new ListNode<>(2,
                new ListNode<>(3,
                new ListNode<>(4,
                new ListNode<>(5,
                new ListNode<>(6,
                        new ListNode<>(7, null)))))));
//        while(L.next != null){
//            System.out.println("Node val is:" + L.data.toString());
//            System.out.println("Next ptr is:" + L.next.toString());
//            L = L.next;
//        }
//        System.out.println("Node val is:" + L.data.toString());


        while(L.next != null){
            newL.data = L.data;
            newL.next = L.next.next;
            L = L.next.next;
        }

        while(newL.next != null){
            System.out.println("Node val is:" + newL.data.toString());
            System.out.println("Next ptr is:" + newL.next.toString());
            newL= newL.next;
        }
        System.out.println("Node val is:" + newL.data.toString());


//        insertAfter(L, new ListNode<>(1, null));
//        checkAnswer(L, Arrays.asList(2, 1, 4, 3));
//        insertAfter(L.next.next, new ListNode<>(10, null));
//        checkAnswer(L, Arrays.asList(2, 1, 4, 10, 3));
//        insertAfter(L.next.next.next.next, new ListNode<>(-1, null));
//        checkAnswer(L, Arrays.asList(2, 1, 4, 10, 3, -1));
    }
}

