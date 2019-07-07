package algoExpert.DP;

/**
 * Created by Raj on 7/6/19.
 */
public class MaxProfitWithKTransaction {
    // O(nk) time | O(nk) space
    public static int maxProfitWithKTransactions(int[] prices, int k) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] profits = new int[k + 1][prices.length];
        for (int t = 1; t < k + 1; t++) {
            int maxThusFar = Integer.MIN_VALUE;
            for (int d = 1; d < prices.length; d++) {
                maxThusFar = Math.max(maxThusFar, profits[t - 1][d - 1] - prices[d - 1]);
                profits[t][d] = Math.max(profits[t][d - 1], maxThusFar + prices[d]);
            }
        }
        return profits[k][prices.length - 1];
    }

    // O(nk) time | O(n) space
    public static int maxProfitWithKTransactionsSpaceOptimized(int[] prices,
                                                               int k) {
        if (prices.length == 0) {
            return 0;
        }
        int[] evenProfits = new int[prices.length];
        int[] oddProfits = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            evenProfits[i] = 0;
            oddProfits[i] = 0;
        }
        for (int t = 1; t < k + 1; t++) {
            int maxThusFar = Integer.MIN_VALUE;
            int[] currentProfits = new int[prices.length];
            int[] previousProfits = new int[prices.length];
            if (t % 2 == 1) {
                currentProfits = oddProfits;
                previousProfits = evenProfits;
            } else {
                currentProfits = evenProfits;
                previousProfits = oddProfits;
            }
            for (int d = 1; d < prices.length; d++) {
                maxThusFar = Math.max(maxThusFar, previousProfits[d - 1] - prices[d - 1]);
                currentProfits[d] = Math.max(currentProfits[d - 1], maxThusFar + prices[d]);
            }
        }
        return k % 2 == 0 ? evenProfits[prices.length - 1] : oddProfits[prices.length - 1];
    }
}
