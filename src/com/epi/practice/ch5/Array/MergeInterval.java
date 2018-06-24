package com.epi.practice.ch5.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Raj on 3/3/18.
 */
public class MergeInterval {

    public static boolean hasOverlap(Interval[] intervals) {
        //   Turn   Intervals   into   Points
        List<Point> points = new ArrayList<Point>();
        List<Interval> result = new ArrayList<Interval>();

        for (Interval interval : intervals) {
            points.add(new Point(interval.getStart(), true   /*isStart*/));
            points.add(new Point(interval.getEnd(), false   /*isStart*/));
        }
        //   Sort   the   Points   in   order   of   time
        //   Sort   order   is   defined   by   the   Comparable   interface,   which   Point implements
        Collections.sort(points);

        //   Loop   through   Points
        int count = 0;
        for (int i = 0; i < points.size(); i++) {
            count = points.get(i).isStart() ? count + 1 : count - 1;

            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    public static List<Interval> merge(List<Interval> intervals) {
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

    public static void main(String[] args) {
        Interval interval = new Interval(1, 3);
        Interval interval2 = new Interval(3, 5);
        Interval interval3 = new Interval(6, 8);
        Interval interval4 = new Interval(7, 9);

        Interval interval1[] = {interval, interval2, interval3, interval4};
        List lst = new ArrayList<Interval>();
        lst.add(interval);
        lst.add(interval2);
        lst.add(interval3);
        lst.add(interval4);

        System.out.println("Does it return true:" + merge(lst).toString());
    }

    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "(" + this.getStart() + "," + this.getEnd() + ")";
        }

    }

    public static class Point implements Comparable<Point> {
        int time;


        boolean isStart;

        public Point(int time, boolean isStart) {
            super();
            this.time = time;
            this.isStart = isStart;
        }

        public int getTime() {
            return time;
        }

        public boolean isStart() {
            return isStart;
        }


        @Override
        public int compareTo(Point other) {
            if (time == other.getTime())
                return 0;
            return time > other.getTime() ? 1 : -1;
        }


    }
}
