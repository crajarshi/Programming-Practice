package Facebook;

import Facebook.IntervalListIntersection.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: true
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRooms {
    public static int minMeetingRooms(int[][] intervals) {
        Map<Integer, Integer> changes = new TreeMap<>();
        for (int[] i : intervals) {
            changes.put(i[0], changes.getOrDefault(i[0], 0) + 1);
            changes.put(i[1], changes.getOrDefault(i[1], 0) - 1);
        }
        int room = 0, maxrooms = 0;
        for (int v : changes.values())
            maxrooms = Math.max(maxrooms, room += v);
        return maxrooms;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {15, 20}, {5, 10},};
        System.out.println(minMeetingRooms(intervals));
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }

    /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
     * <p>
     * Example 1:
     * <p>
     * Input: [[0, 30],[5, 10],[15, 20]]
     * Output: 2
     * Example 2:
     * <p>
     * Input: [[7,10],[2,4]]
     * Output: 1
     * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     */
    public int minMeetingRooms(Interval[] intervals) {
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
}
