package Facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 * <p>
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 */
public class IntervalListIntersection {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0)
            return new int[][]{};
        List<int[]> res = new ArrayList<>();

        int i = 0, j = 0;
        int startMax, endMin;
        while (i < A.length && j < B.length) {
            startMax = Math.max(A[i][0], B[j][0]);
            endMin = Math.min(A[i][1], B[j][1]);

            if (endMin >= startMax)
                res.add(new int[]{startMax, endMin});

            if (A[i][1] == endMin) i++;
            if (B[j][1] == endMin) j++;
        }

        return res.toArray(new int[res.size()][2]);
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {//[[[1,2],[5,6]],[[1,3]],[[4,10]]]
        List<Interval> result = new ArrayList<>();
/**
 The idea is to just add all the intervals to the priority queue.
 (NOTE that it is not matter how many different people are there for the algorithm. becuase we just need to find a gap in the time line.

 priority queue - sorted by start time, and for same start time sort by either largest end time or smallest (it is not matter).
 Everytime you poll from priority queue, just make sure it doesn't intersect with previous interval.
 This mean that there is no common interval. Everyone is free time.
 */
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        schedule.forEach(e -> pq.addAll(e));//[[[1,2],[1,3]],[[4,10]],[[5,6]]]

        Interval temp = pq.poll();
        while (!pq.isEmpty()) {
            if (temp.end < pq.peek().start) { // no intersect
                result.add(new Interval(temp.end, pq.peek().start));//[3,4]
                temp = pq.poll(); // becomes the next temp interval
            } else { // intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result;
    }

    /**
     * You are given a list schedule of employees, which represents the
     * working time for each employee.
     * <p>
     * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
     * <p>
     * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
     * <p>
     * Example 1:
     * <p>
     * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
     * Output: [[3,4]]
     * Explanation:
     * There are a total of three employees, and all common
     * free time intervals would be [-inf, 1], [3, 4], [10, inf].
     * We discard any intervals that contain inf as they aren't finite.
     * <p>
     * <p>
     * Example 2:
     * <p>
     * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
     * Output: [[5,6],[7,9]]
     * <p>
     * <p>
     * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
     * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
     * <p>
     * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
     * <p>
     * <p>
     * Definition for an interval.
     **/
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}

