package LCPremium.Google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 * <p>
 * Example:
 * <p>
 * MovingAverage m = new MovingAverage(3); m.next(1) = 1 m.next(10) = (1 + 10) /
 * 2 m.next(3) = (1 + 10 + 3) / 3 m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverage {
    //The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.
//    private int [] window;
//    private int n, insert;
//    private long sum;
//
//    /** Initialize your data structure here. */
//    public MovingAverage(int size) {
//        window = new int[size];
//        insert = 0;
//        sum = 0;
//    }
//
//    public double next(int val) {
//        if (n < window.length)  n++;
//        sum -= window[insert];
//        sum += val;
//        window[insert] = val;
//        insert = (insert + 1) % window.length;
//
//        return (double)sum / n;
//    }

    Queue<Integer> q;
    double sum = 0;
    int size;

    public MovingAverage(int s) {
        q = new LinkedList();
        size = s;
    }

    public double next2(int val) {
        if (q.size() == size) {
            sum = sum - q.poll();
        }
        q.offer(val);
        sum += val;
        return sum / q.size();
    }
/**
 * Essentially, we just need to keep track of the sum of the current window as we go.
 * This prevents an O(n) traversal over the Queue as we add new numbers to get the new average.
 * If we need to evict then we just subtract that number off of our sum and divide by the size.
 */

//    private double previousSum = 0.0;
//    private int maxSize;
//    private Queue<Integer> currentWindow;
//
//    public MovingAverage(int size) {
//        currentWindow = new LinkedList<Integer>();
//        maxSize = size;
//    }
//
//    public double next(int val) {
//        if (currentWindow.size() == maxSize)
//        {
//            previousSum -= currentWindow.remove();
//        }
//
//        previousSum += val;
//        currentWindow.add(val);
//        return previousSum / currentWindow.size();
}
