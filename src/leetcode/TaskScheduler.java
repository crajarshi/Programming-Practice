package leetcode;

import java.util.Arrays;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z
 * where different letters represent different tasks.Tasks could be done without original order.
 * Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * <p>
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 * <p>
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */
public class TaskScheduler {
    /**
     * // (c[25] - 1) * (n + 1) + 25 - i  is frame size
     * // when inserting chars, the frame might be "burst", then tasks.length takes precedence
     * // when 25 - i > n, the frame is already full at construction, the following is still valid.
     * <p>
     * First consider the most frequent characters, we can determine their relative positions first and use them as a frame to insert the remaining less frequent characters. Here is a proof by construction:
     * <p>
     * Let F be the set of most frequent chars with frequency k.
     * We can create k chunks, each chunk is identical and is a string consists of chars in F in a specific fixed order.
     * Let the heads of these chunks to be H_i; then H_2 should be at least n chars away from H_1, and so on so forth; then we insert the less frequent chars into the gaps between these chunks sequentially one by one ordered by frequency in a decreasing order and try to fill the k-1 gaps as full or evenly as possible each time you insert a character. In summary, append the less frequent characters to the end of each chunk of the first k-1 chunks sequentially and round and round, then join the chunks and keep their heads' relative distance from each other to be at least n.
     * <p>
     * Examples:
     * <p>
     * AAAABBBEEFFGG 3
     * <p>
     * here X represents a space gap:
     * <p>
     * Frame: "AXXXAXXXAXXXA"
     * insert 'B': "ABXXABXXABXXA" <--- 'B' has higher frequency than the other characters, insert it first.
     * insert 'E': "ABEXABEXABXXA"
     * insert 'F': "ABEFABEXABFXA" <--- each time try to fill the k-1 gaps as full or evenly as possible.
     * insert 'G': "ABEFABEGABFGA"
     * AACCCBEEE 2
     * <p>
     * 3 identical chunks "CE", "CE CE CE" <-- this is a frame
     * insert 'A' among the gaps of chunks since it has higher frequency than 'B' ---> "CEACEACE"
     * insert 'B' ---> "CEABCEACE" <----- result is tasks.length;
     * AACCCDDEEE 3
     * <p>
     * 3 identical chunks "CE", "CE CE CE" <--- this is a frame.
     * Begin to insert 'A'->"CEA CEA CE"
     * Begin to insert 'B'->"CEABCEABCE" <---- result is tasks.length;
     * ACCCEEE 2
     * <p>
     * 3 identical chunks "CE", "CE CE CE" <-- this is a frame
     * Begin to insert 'A' --> "CEACE CE" <-- result is (c[25] - 1) * (n + 1) + 25 -i = 2 * 3 + 2 = 8
     *
     * Find the most frequent chars(tasks) and use them to create Frames. We call these chars Delimiters
     Suppose we have F Frames and F+1 Delimiters, and for simplicity, we assume the Delimiter is a char 'A'
     Now we setup all Frames as something like this: A----A----A----A----A
     We fill the next most frequent chars into these Frames. Keep doing this until (i)
     there is no remaining chars or (ii) there is no empty slot in Frames
     Now we're done filling the Frames. We consider the remaining chars.

     (i) No remaining chars means we're done. The length of the sequence is F * length(Frame) + length(Delimiter)
     (ii) No empty slots in Frames means we have to decide where to put the remaining chars.

     For (ii), Suppose the first char of the remaining chars is 'V', and the length of 'V's is L,
     with L <= F (or it'll be in the Delimiters).
     And there are K of them filled into the Frames at the last positions.
     So there are L-K 'V's left and F-K Frames containing no 'V'. Because L-K <= F-K,
     we can append L-K 'V' to F-K Frames without violating the rule.
     After we fill the first remaining chars 'V',
     we pick the next one and append it to the Frames (length of the chars <= F) .
     And keep doing this until no char remains.
     The length of the sequence for (ii) is length(chars)
     */
    public static int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for (char t : tasks) {
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

    public static void main(String[] args) {
//        leastInterval({'A','A','A',"B","B","B"},2);
    }
}
