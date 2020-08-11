package LCPremium.Blind75;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * components in an undirected graph.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * <p>
 * 0          3 |          | 1 --- 2    4
 * <p>
 * Output: 2 Example 2:
 * <p>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * <p>
 * 0           4 |           | 1 --- 2 --- 3
 * <p>
 * Output:  1 Note: You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will
 * not appear together in edges.
 */

public class ConnectedComponentsCount {
    /**
     * This is 1D version of Number of Islands II. For more explanations, check
     * out this 2D Solution.
     * <p>
     * <p>
     * n points = n islands = n trees = n roots. With each edge added, check
     * which island is e[0] or e[1] belonging to. If e[0] and e[1] are in same
     * islands, do nothing. Otherwise, union two islands, and reduce islands
     * count by 1. Bonus: path compression can reduce time by 50%. Hope it
     * helps!
     */

    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) roots[i] = i;

        for (int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            if (root1 != root2) {
                roots[root1] = root2;  // union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        while (roots[id] != id) {
            roots[id] = roots[roots[id]];  // optional: path compression
            id = roots[id];
        }
        return id;
    }

    /**
     * Nice! Got the same solution for DFS. I also posted a BFS solution. For others the thinking is if you run DFS from each of the nodes,
     * all connected components will be visited if they are a part of the initial node to be explored.
     * If not then there is some other connected component to be found from another node.
     * Once the DFS finishes we increment the count because this means we're exploring another set of connected components.
     *
     * For BFS its the same idea, except where do we start BFS from?
     * If you start it from any node you may only find that one connected component,
     * so instead you start it from all nodes and visit the max number of nodes using BFS and only increment the count once you visit a new node from the adjacency list,
     * meaning you only increment the count once you're exploring a new connected component:
     */

    /**
     * public int countComponentsDFS(int n, int[][] edges) {
     *     HashMap<Integer, List<Integer>> adjList = new HashMap<>();
     *     for(int node = 0; node < n; node++) {
     *         adjList.put(node, new ArrayList<>());
     *     }
     *
     *     for(int[] edge : edges) {
     *         adjList.get(edge[0]).add(edge[1]);
     *         adjList.get(edge[1]).add(edge[0]);
     *     }
     *
     *     int connectedComponents = 0;
     *     HashSet<Integer> visited = new HashSet<>();
     *     for(Integer node : adjList.keySet()) {
     *         if(!visited.contains(node)) {
     *             connectedComponents++;
     *             dfs(adjList, visited, node);
     *         }
     *     }
     *     return connectedComponents;
     * }
     *
     * private void dfs(HashMap<Integer, List<Integer>> adjList, HashSet<Integer> visited, int node) {
     *     visited.add(node);
     *     for(Integer neighbor : adjList.get(node)) {
     *         if(!visited.contains(neighbor)) {
     *             dfs(adjList, visited, neighbor);
     *         }
     *     }
     * }
     *
     * public int countComponentsBFS(int n, int[][] edges) {
     *     HashMap<Integer, List<Integer>> adjList = new HashMap<>();
     *     for(int node = 0; node < n; node++) {
     *         adjList.put(node, new ArrayList<>());
     *     }
     *
     *     for(int[] edge : edges) {
     *         adjList.get(edge[0]).add(edge[1]);
     *         adjList.get(edge[1]).add(edge[0]);
     *     }
     *
     *     int connectedComponents = 0;
     *     HashSet<Integer> visited = new HashSet<>();
     *     Queue<Integer> queue = new LinkedList<>();
     *     for(Integer node : adjList.keySet()) {
     *         if(!visited.contains(node)) {
     *             connectedComponents++;
     *             queue.offer(node);
     *             visited.add(node);
     *         }
     *
     *         while(!queue.isEmpty()) {
     *             int curr = queue.poll();
     *             for(Integer neighbor : adjList.get(curr)) {
     *                 if(!visited.contains(neighbor)) {
     *                     queue.offer(neighbor);
     *                     visited.add(neighbor);
     *                 }
     *             }
     *         }
     *     }
     *
     *     return connectedComponents;
     * }
     */

}
