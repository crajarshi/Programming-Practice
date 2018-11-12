package leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * if the interviewer twists the question slightly by
 * giving the difference array of prices, Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7} this solution will work.
 * <p>
 * Explanation : -
 * Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, and
 * find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
 */
public class BestTimeToBuyAndSell {
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i - 1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
}
