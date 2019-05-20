package algoExpert.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * You are given a Node class that has a name and an array of optional children Nodes.
 * When put together, Nodes form a simple tree-like structure.
 * Implement the breadthFirstSearch method on the Node class,
 * which takes in an empty array, traverses the tree using the
 * Breadth-first Search approach (specifically navigating the tree from left to right),
 * stores all of the Nodes' names in the input array, and returns it.
 * <p>
 * Sample input:
 * A
 * / | \
 * B  C  D
 * / \       / \
 * E    F  G   H
 * / \    \
 * I   J    K
 * Sample output: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"]
 */
public class BFS {
    public static void main(String[] args) {

        BFS.Node test1;
        test1 = new BFS.Node("A");
        test1.addChild("B").addChild("C");
        test1.children.get(0).addChild("D");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        ArrayList<String> inputArray = new ArrayList<String>();

        boolean result = test1.breadthFirstSearch(inputArray).equals(expected);
        System.out.println(result);
    }

    static class Node {
        String name;
        ArrayList<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        // O(v + e) time | O(v) space
        public ArrayList<String> breadthFirstSearch(ArrayList<String> array) {
            ArrayDeque<Node> queue = new ArrayDeque<Node>();
            queue.add(this);
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                array.add(current.name);
                for (int i = 0; i < current.children.size(); i++) {
                    queue.add(current.children.get(i));
                }
            }
            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
