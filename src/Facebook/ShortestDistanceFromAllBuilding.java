package Facebook;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * <p>
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 * <p>
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * <p>
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * <p>
 * Output: 7
 * <p>
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 * the point (1,2) is an ideal empty land to build a house, as the total
 * travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
// TC O(m2n2)
public class ShortestDistanceFromAllBuilding {
    static final int[] delta = new int[]{0, 1, 0, -1, 0};
    int min = Integer.MAX_VALUE;


    public int shortestDistance(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        int start = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    bfsVisit(grid, dist, i, j, --start);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * don't use a fresh "visited" for each BFS. Instead, I walk only onto the cells that were reachable from all previous buildings.
     * From the first building I only walk onto cells where grid is 0, and make them -1.
     * From the second building I only walk onto cells where grid is -1, and I make them -2. And so on.
     * /**
     * Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to
     * this building. After we do this for all the buildings, we can get the sum of shortest distance
     * from every '0' to all reachable buildings. This value is stored
     * in 'distance[][]'
     */


    private void bfsVisit(int[][] grid, int[][] dist, int row, int col, int start) {
        Deque<int[]> que = new ArrayDeque<int[]>();
        que.offer(new int[]{row, col});
        int level = 0;
        min = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            int size = que.size();
            level++;
            for (int k = 0; k < size; k++) {
                int[] node = que.poll();
                for (int i = 1; i < delta.length; i++) {
                    int newRow = node[0] + delta[i - 1];
                    int newCol = node[1] + delta[i];
                    if (newRow >= 0 && newRow < grid.length &&
                            newCol >= 0 && newCol < grid[0].length &&
                            grid[newRow][newCol] == start) {
                        que.offer(new int[]{newRow, newCol});
                        dist[newRow][newCol] += level;
                        min = Math.min(min, dist[newRow][newCol]);
                        grid[newRow][newCol]--;
                    }
                }
            }
        }
    }

    /**
     * The main idea is the following:
     * <p>
     * Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to
     * this building. After we do this for all the buildings, we can get the sum of shortest distance
     * from every '0' to all reachable buildings. This value is stored
     * in 'distance[][]'. For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance from this block to all reachable buildings.
     * Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)
     * <p>
     * We also count how many building each '0' can be reached. It is stored in reach[][].
     * This can be done during the BFS. We also need to count how many total buildings are there in the matrix, which is stored in 'buildingNum'.
     * <p>
     * Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)
     * <p>
     * The total time complexity will be O(m^2*n^2), which is quite high!. Please let me know if I did the analysis wrong or you have better solution.
     */

    public int shortestDistance2(int[][] grid) {
        if (grid == null || grid[0].length == 0) return 0;
        final int[] shift = new int[]{0, 1, 0, -1, 0};

        int row = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<int[]> myQueue = new LinkedList<int[]>();
                    myQueue.offer(new int[]{i, j});

                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1;

                    while (!myQueue.isEmpty()) {
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();

                            for (int k = 0; k < 4; k++) {
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];

                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                        && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                                    //The shortest distance from [nextRow][nextCol] to thic building
                                    // is 'level'.
                                    distance[nextRow][nextCol] += level;
                                    reach[nextRow][nextCol]++;

                                    isVisited[nextRow][nextCol] = true;
                                    myQueue.offer(new int[]{nextRow, nextCol});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;


    }
}
