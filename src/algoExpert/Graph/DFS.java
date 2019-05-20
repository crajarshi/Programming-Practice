package algoExpert.Graph;

import java.util.ArrayList;

/**
 * You are given a Node class that has a name and an array of optional children Nodes.
 * When put together, Nodes form a simple tree-like structure.
 * Implement the depthFirstSearch method on the Node class, which takes in an empty array,
 * traverses the tree using the Depth-first Search approach (specifically
 * navigating the tree from left to right),
 * stores all the of the Nodes' names in the input array, and returns it.
 * <p>
 * Sample input:
 * A
 * / | \
 * B  C  D
 * / \       / \
 * E    F  G   H
 * / \    \
 * I   J    K
 * Sample output: ["A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H"]
 */
public class DFS {
    static class Node {
        String name;
        ArrayList<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        // O(v + e) time | O(v) space
        public ArrayList<String> depthFirstSearch(ArrayList<String> array) {
            array.add(this.name);
            for (int i = 0; i < children.size(); i++) {
                children.get(i).depthFirstSearch(array);
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
