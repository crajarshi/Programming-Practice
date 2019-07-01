package Facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null)
            return res.toArray(new int[0][]);

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int[] i : intervals) {
            if (i[0] <= end) {
                end = Math.max(end, i[1]);
            } else {
                res.add(new int[]{start, end});
                start = i[0];
                end = i[1];
            }
        }
        //// Add the last interval
        res.add(new int[]{start, end});
        // toArray() method returns an array of type Object(Object[]). We need to typecast it to Integer before using as Integer objects.
        // If we do not typecast, we get compilation error. Consider the following example:


        return res.toArray(new int[0][]);

    }


//    public List<Interval> merge(List<Interval> intervals) {
//        if (intervals.size() <= 1)
//            return intervals;
//
//        // Sort by ascending starting point using an anonymous Comparator
//        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
//
//        List<Interval> result = new LinkedList<Interval>();
//        int start = intervals.get(0).start;
//        int end = intervals.get(0).end;
//
//        for (Interval interval : intervals) {
//            if (interval.start <= end) // Overlapping intervals, move the end if needed
//                end = Math.max(end, interval.end);
//            else {                     // Disjoint intervals, add the previous one and reset bounds
//                result.add(new Interval(start, end));
//                start = interval.start;
//                end = interval.end;
//            }
//        }
//
//        // Add the last interval
//        result.add(new Interval(start, end));
//        return result;
//    }
}
