package Facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 * <p>
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 * <p>
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 * <p>
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 * <p>
 * How many total friend requests are made?
 * <p>
 * Example 1:
 * <p>
 * Input: [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 * <p>
 * Input: [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 * <p>
 * Input: [20,30,100,110,120]
 * Output:
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 * <p>
 * <p>
 * Notes:
 * <p>
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 */
public class FriendsOfAppropriateAge {

    public static int numFriendRequests(int[] ages) {
        int res = 0;
        int[] numInAge = new int[121], sumInAge = new int[121];

        for (int i : ages)
            numInAge[i]++;

        for (int i = 1; i <= 120; ++i)
            sumInAge[i] = numInAge[i] + sumInAge[i - 1];

        for (int i = 15; i <= 120; ++i) {
            if (numInAge[i] == 0) continue;
            int count = sumInAge[i] - sumInAge[i / 2 + 7];
            res += count * numInAge[i] - numInAge[i]; //people will not friend request themselves, so  - numInAge[i]
        }
        return res;
    }

    public static int numFriendRequests2(int[] ages) {
        /**
         * Write a sub function request(a, b) to check if age a will friend requests age b.
         I just copy it from description: return !(condition1 || condition2 || condition3)
         (I don't even realize that the third condition age[B] > 100 && age[A] < 100 is in fact redundant with the second age[B] > age[A].)

         Count nunmber of all ages to a map.
         Because we have at most 20000 ages but only in range [1, 120].

         For each age a and each age b != a, if request(a, b), we will make count[a] * count[b] requests.

         For each age a, if request(a, a), we will make count[a] * (count[a] - 1) requests.
         */
        Map<Integer, Integer> count = new HashMap<>();
        for (int age : ages) count.put(age, count.getOrDefault(age, 0) + 1);
        int res = 0;
        for (Integer a : count.keySet())
            for (Integer b : count.keySet())
                if (request(a, b))
                    res += count.get(a) * (count.get(b) - (a == b ? 1 : 0));
        return res;
    }

    private static boolean request(int a, int b) {
        return !(b <= 0.5 * a + 7 || b > a || (b > 100 && a < 100));
    }

    public static void main(String[] args) {
        int[] arr = {20, 30, 100, 110, 120};
        System.out.println(numFriendRequests2(arr));
    }
}
