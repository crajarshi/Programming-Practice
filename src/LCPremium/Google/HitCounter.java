package LCPremium.Google;

/**
 * Design a hit counter which counts the number of hits received in the past 5
 * minutes.
 * <p>
 * Each function accepts a timestamp parameter (in seconds granularity) and you
 * may assume that calls are being made to the system in chronological order
 * (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 * <p>
 * It is possible that several hits arrive roughly at the same time.
 * <p>
 * Example:
 * <p>
 * HitCounter counter = new HitCounter();
 * <p>
 * // hit at timestamp 1. counter.hit(1);
 * <p>
 * // hit at timestamp 2. counter.hit(2);
 * <p>
 * // hit at timestamp 3. counter.hit(3);
 * <p>
 * // get hits at timestamp 4, should return 3. counter.getHits(4);
 * <p>
 * // hit at timestamp 300. counter.hit(300);
 * <p>
 * // get hits at timestamp 300, should return 4. counter.getHits(300);
 * <p>
 * // get hits at timestamp 301, should return 3. counter.getHits(301); Follow
 * up: What if the number of hits per second could be very large? Does your
 * design scale?
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * O(s) s is total seconds in given time interval, in this case 300.
 basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds.
 hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time.
 If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.

 AtomicIntegerArray won't solve the issue as other points out, here is a ReentrantReadWriteLock solution.
 The advantage of ReentrantReadWriteLock over Synchonized keyword is multiple getHits(t) (read operation) won't block each other,
 hence increase the performance.
 */
class HitCounter {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    // store each last get hit timestamp with that bucket
    int[] times;
    // store the number of hit for that bucket
    int[] hits;

    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        w.lock();
        try {
            int idx = timestamp % 300;
            if (times[idx] != timestamp) {
                // not in the same 5 minute window
                times[idx] = timestamp;
                hits[idx] = 1;
            } else {
                hits[idx]++;
            }

        } finally {
            w.unlock();
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int sum = 0;
        r.lock();
        try {
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) sum += hits[i];
            }
        } finally {
            r.unlock();
        }
        return sum;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */