package org.linkedlist;

public class LinkedList {

    private Node head;

    public LinkedList() {
        head = new Node();
        head.value = 0;
        head.link = null;
    }

    public boolean insertItemBeginning(int item) {
        Node node = new Node();
        node.value = item;
        node.link = head;
        head = node;
        return true;
    }

    public boolean deleteItem(int item) {
        if (head.link.value == item) {
            head.link = head.link.link;
            return true;
        } else {
            Node prev = head;
            Node next = head.link;
            while (true) {
                if (next == null || next.value == item) {
                    break;
                } else {
                    prev = next;
                    next = next.link;
                }

            }
            if (next != null) {
                prev.link = next.link;
                return true;
            } else return false;
        }
    }

    public void printLinkedList() {
        Node node = head.link;
        while (node != null) {
            System.out.println("******The value is :" + node.value + "*****");
            node = node.link;
        }
    }

    class Node {

        private int value;
        private Node link;
    }
}
