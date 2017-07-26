package org.DP;

public class Knapsack {
    public int bottomUpDP(int val[], int wt[], int W) {
        int grid[][] = new int[val.length + 1][W + 1];
        for (int row = 0; row <= val.length; row++) {
            for (int col = 0; col <= W; col++) {
                if (row == 0 || col == 0) {
                    grid[row][col] = 0;// intitialize the (0,0 with 0)
                    continue;
                }
                if (col - wt[row - 1] >= 0) { // This is the initial check to see if the value of the column is greater or equal to row no
                    //
                    grid[row][col] = Math.max(grid[row - 1][col], grid[row - 1][col - wt[row - 1]] + val[row - 1]);// you take the max of (upper row,col and (adding the upper row value of the column deducting same no of wt and the current value)
                } else {
                    grid[row][col] = grid[row - 1][col];// if you're comparing with a column say 3 with row 2 you take the value from upper row.
                }
            }
        }
        return grid[val.length][W];
    }

    public static void main(String args[]) {
        Knapsack k = new Knapsack();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int r = k.bottomUpDP(val, wt, 30);
        System.out.println(r);

        int coins[] = {1,5,10,25};
//        int result = k.bottomUpDP(val,coins,)
    }


}
