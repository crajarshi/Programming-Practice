package Facebook;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Raj on 6/30/19.
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //For one, it wouldn't be thread safe. If you had multiple threads calling this same function, they'd be writing to the same map,
        // and clobbering each other. This implementation is safer,
        // because it gives each call its own copy of a HashMap, and they don't have to worry about it.
        // The question doesn't explicitly say to be concerned about concurrency, but it never hurts to be proactive.

        HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        return dfs(node, map);
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map) {
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

    class UndirectedGraphNode {
        public int label;
        public List<UndirectedGraphNode> neighbors;

        public UndirectedGraphNode() {
        }

        public UndirectedGraphNode(int _val) {
            label = _val;
        }
    }
}
