package LCPremium.Medium;

import java.util.*;


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
public class MeetingRoom2 {
    /**
     * This is also Meeting ROOM 1 problem. Given an array of meeting time
     * intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si <
     * ei), determine if a person could attend all meetings.
     * <p>
     * Input: [[0,30],[5,10],[15,20]] Output: false Input: [[7,10],[2,4]]
     * Output: true
     **/

    public static boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }


//    public List<Interval> merge(List<Interval> intervals) {
//        if (intervals.size() <= 1)
//            return intervals;
//
//        // Sort by ascending starting point using an anonymous Comparator
//      intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
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

    public static void main(String[] args) {
        Interval interval1 = new Interval(0, 30);
        Interval interval2 = new Interval(5, 10);
        Interval interval3 = new Interval(15, 20);

        Interval[] intervals = new Interval[3];
        intervals[0] = interval1;
        intervals[1] = interval2;
        intervals[2] = interval3;

        System.out.println(canAttendMeetings(intervals));
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null)
            return res.toArray(new int[0][]);
        //Sort by ascending starting point using an anonymous Comparator
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

    /**
     * Given a collection of intervals, merge all overlapping intervals. Input:
     * [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]] Explanation:
     * <p>
     * The idea is to sort the intervals by their starting points. Then, we take
     * the first interval and compare its end with the next intervals starts. As
     * long as they overlap, we update the end to be the max end of the
     * overlapping intervals. Once we find a non overlapping interval, we can
     * add the previous "extended" interval and start over.
     * <p>
     * Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the
     * resulting algorithm takes O(n log(n)).
     * <p>
     * I used an a lambda comparator (Java 8) and a for-each loop to try to keep
     * the code clean and simple.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new LinkedList<Interval>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }

    /**
     * THis solution does use extra space O(n) but it doesn't alter the order of
     * input.
     *
     * @param intervals
     * @return
     */
    public List<Interval> mergePQ(List<Interval> intervals) {
        List<Interval> result = new LinkedList<Interval>();
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(intervals.size(), (a, b) -> (a.start - b.start));
        for (int i = 0; i < intervals.size(); i++) {
            pq.offer(intervals.get(i));
        }
        while (pq.size() > 1) {
            Interval i1 = pq.poll();
            Interval i2 = pq.poll();
            if (i1.end >= i2.start) {
                Interval node = new Interval(i1.start, Math.max(i2.end, i1.end));
                pq.offer(node);
            } else {
                result.add(i1);
                pq.offer(i2);
            }
        }
        result.add(pq.poll());
        return result;
    }

    /**
     * This is also Meeting Room 2 problem.
     * Given an array of meeting time intervals consisting of
     * start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     * Input: [[0, 30],[5, 10],[15, 20]]
     * Output: 2
     * Input: [[7,10],[2,4]]
     * Output: 1
     *
     * @param intervals
     * @return
     */

    public int minMeetingRooms12(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }

    public int minMeetingRoomsPQ(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        pq.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // if the current meeting starts right after
            // there's no need for a new room, merge the interval
            if (intervals[i].start >= pq.peek().end) {
                pq.poll();
            }
            // otherwise, this meeting needs a new room
            pq.offer(intervals[i]);
        }
        return pq.size();
    }

    //Definition for an interval.
    public static class Interval {
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
