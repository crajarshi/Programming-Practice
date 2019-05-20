package algoExpert.Heap;

import java.util.*;

/**
 * Write a class that can support the following two functionalities:
 * 1) the continuous insertion of numbers and
 * 2) the instant (O(1) time) retrieval of the median of the numbers that have been inserted thus far.
 * The getMedian() method, which handles the retrieval of the median, has already been written for you.
 * You simply have to write the insert() method.
 * <p>
 * Sample input: insert(5), insert(10), getMedian(), insert(100), getMedian()
 * Sample output: -, -, 7.5, -, 10
 */
public class ContiniousMedian {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {

        // minHeap stores the larger half seen so far.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // maxHeap stores the smaller half seen so far.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());
        List<Double> result = new ArrayList<>();

        while (sequence.hasNext()) {
            int x = sequence.next();
            minHeap.add(x);
            maxHeap.add(minHeap.remove());
            // Ensure minHeap and maxHeap have equal number of elements if
            // an even number of elements is read; otherwise, minHeap must have
            // one more element than maxHeap.
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove());
            }

            result.add((minHeap.size() == maxHeap.size()
                    ? 0.5 * (minHeap.peek() + maxHeap.peek())
                    : (double) minHeap.peek()));
        }
        return result;
    }

    public static void main(String[] args) {
        Iterator<Integer> sequence = Arrays.asList(5, 10, 100, 200, 6).iterator();
        System.out.println(onlineMedian(sequence));
    }

}
