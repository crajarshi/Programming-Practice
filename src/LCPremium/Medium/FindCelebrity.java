package LCPremium.Medium;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among
 * them, there may exist one celebrity. The definition of a celebrity is that
 * all the other n - 1 people know him/her but he/she does not know any of
 * them.
 * <p>
 * Now you want to find out who the celebrity is or verify that there is not
 * one. The only thing you are allowed to do is to ask questions like: "Hi, A.
 * Do you know B?" to get information of whether A knows B. You need to find out
 * the celebrity (or verify there is not one) by asking as few questions as
 * possible (in the asymptotic sense).
 * <p>
 * You are given a helper function bool knows(a, b) which tells you whether A
 * knows B. Implement a function int findCelebrity(n). There will be exactly one
 * celebrity if he/she is in the party. Return the celebrity's label if there is
 * a celebrity in the party. If there is no celebrity, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: graph = [ [1,1,0], [0,1,0], [1,1,1] ] Output: 1 Explanation: There are
 * three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows
 * person j, otherwise graph[i][j] = 0 means person i does not know person j.
 * The celebrity is the person labeled as 1 because both 0 and 2 know him but 1
 * does not know anybody. Example 2:
 * <p>
 * <p>
 * Input: graph = [ [1,0,1], [1,1,0], [0,1,1] ] Output: -1 Explanation: There is
 * no celebrity.
 * <p>
 * <p>
 * Note:
 * <p>
 * The directed graph is represented as an adjacency matrix, which is an n x n
 * matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0
 * means the contrary. Remember that you won't have direct access to the
 * adjacency matrix.
 */
public class FindCelebrity {

    public int findCelebrity(int n) {
        int possibleCelebrity = 0;
        for (int i = 1; i < n; i++) {
            // if true, then possibleCelebrity cannot be the real celebrity because real celebrity should know nobody
            // if false, then i cannot be the celebrity because celebrity should be known by all the other ones
            if (knows(possibleCelebrity, i)) {
                possibleCelebrity = i;
            }
        }

        for (int i = 0; i < n; i++) {
            // this possibleCelebrity do know i
            // real celebrity should know nobody, so the possibleCelebrity is not the real celebrity
            if (i != possibleCelebrity && knows(possibleCelebrity, i)) {
                return -1;
            }

            // this possibleCelebrity are not known by i
            // real celebrity should be known by all the other ones, so the possibleCelebrity is not the real celebrity
            if (i != possibleCelebrity && !knows(i, possibleCelebrity)) {
                return -1;
            }
        }

        return possibleCelebrity;
    }

    private boolean knows(int a, int b) {
        return true;
    }
}
