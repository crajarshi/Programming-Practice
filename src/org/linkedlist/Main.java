package org.linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertItemBeginning(6);
        linkedList.insertItemBeginning(4);
        linkedList.insertItemBeginning(2);
        linkedList.insertItemBeginning(10);
        linkedList.printLinkedList();
        System.out.println("Deleteing 1st time");
        linkedList.deleteItem(4);
        linkedList.printLinkedList();
        System.out.println("Deleteing 2nd time");
        linkedList.deleteItem(5);
        linkedList.printLinkedList();
        System.out.println("Deleteing 3rd time");
        linkedList.deleteItem(2);
        linkedList.printLinkedList();



    }
}
