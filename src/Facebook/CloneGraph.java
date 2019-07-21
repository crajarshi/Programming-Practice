package Facebook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a reference of a node in a connected undirected graph,
 * return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * Input:
 {"$id":"1","neighbors":[{"$id":"2","neighbors":
 [{"$ref":"1"},{"$id":"3","neighbors":
 [{"$ref":"2"},{"$id":"4","neighbors":
 [{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

 Explanation:
 Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 Node 4's value is 4, and it has two neighbors: Node 1 and 3.

 Note:

 The number of nodes will be between 1 and 100.
 The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 You must return the copy of the given node as a reference to the cloned graph.


 */
public class CloneGraph {
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //For one, it wouldn't be thread safe. If you had multiple threads calling this same function, they'd be writing to the same map,
        // and clobbering each other. This implementation is safer,
        // because it gives each call its own copy of a HashMap, and they don't have to worry about it.
        // The question doesn't explicitly say to be concerned about concurrency, but it never hurts to be proactive.

        HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        return dfs(node, map);
    }

    private static UndirectedGraphNode dfs(UndirectedGraphNode node,
                                           HashMap<Integer, UndirectedGraphNode> map) {
        if (node == null) return null;
        if (map.containsKey(node.label)) {
            return map.get(node.label);
        } else {
            UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
            map.put(node.label, clone);
            for (int i = 0; i < node.neighbors.size(); i++) {
                clone.neighbors.add(dfs(node.neighbors.get(i), map));
            }
            return clone;
        }
    }

    public static void main(String[] args) {
        UndirectedGraphNode und1 = new UndirectedGraphNode(1);
        UndirectedGraphNode und2 = new UndirectedGraphNode(2);
        UndirectedGraphNode und3 = new UndirectedGraphNode(3);
        UndirectedGraphNode und4 = new UndirectedGraphNode(4);
        und1.neighbors.add(new UndirectedGraphNode(2));
        und1.neighbors.add(new UndirectedGraphNode(und4.label));
        und2.neighbors.add(new UndirectedGraphNode(und1.label));
        und2.neighbors.add(new UndirectedGraphNode(und3.label));
        und3.neighbors.add(new UndirectedGraphNode(und2.label));
        und3.neighbors.add(new UndirectedGraphNode(und4.label));
        und4.neighbors.add(new UndirectedGraphNode(und1.label));
        und4.neighbors.add(new UndirectedGraphNode(und3.label));

        System.out.println(cloneGraph(und1));
    }

    // TC --> O(V) + O(E)
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        dfs(map, node);
        return map.get(node);
    }

    private void dfs(Map<Node, Node> map, Node curr) {
        if (map.containsKey(curr)) return;
        map.put(curr, new Node(curr.val));
        for (Node next : curr.neighbors) {
            dfs(map, next);
            map.get(curr).neighbors.add(map.get(next));
        }
    }

    static class UndirectedGraphNode {
        public int label;
        public List<UndirectedGraphNode> neighbors;

        public UndirectedGraphNode() {
        }

        public UndirectedGraphNode(int _val) {
            label = _val;
        }
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }
    }
}
