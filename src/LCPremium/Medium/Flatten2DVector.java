package LCPremium.Medium;

import java.util.Iterator;
import java.util.List;

/**
 * Design and implement an iterator to flatten a 2d vector. It should support
 * the following operations: next and hasNext.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 * <p>
 * iterator.next(); // return 1 iterator.next(); // return 2 iterator.next(); //
 * return 3 iterator.hasNext(); // return true iterator.hasNext(); // return
 * true iterator.next(); // return 4 iterator.hasNext(); // return false
 * <p>
 * <p>
 * Notes:
 * <p>
 * Please remember to RESET your class variables declared in Vector2D, as
 * static/class variables are persisted across multiple test cases. Please see
 * here for more details. You may assume that next() call will always be valid,
 * that is, there will be at least a next element in the 2d vector when next()
 * is called.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * As an added challenge, try to code it using only iterators in C++ or
 * iterators in Java.
 */
public class Flatten2DVector {


    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    public int next() {
        hasNext();
        return j.next();
    }

    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }


    class Vector2D {

        int[][] v;
        int i = 0, j = 0;

        public Vector2D(int[][] v) {
            this.v = v;
        }

        public int next() {
            if (hasNext()) return v[i][j++];
            else return -1;
        }

        public boolean hasNext() {
            while (i < v.length && j == v[i].length) {  // Move to next available vector
                i++;
                j = 0;
            }
            return i < v.length;
        }
    }
}
