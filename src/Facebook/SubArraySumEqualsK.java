package Facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public int subarraySum3(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int num : nums) {
            sum += num;
            result += preSum.getOrDefault(sum - k, 0);
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    /**
     * Consider the below example:
     * array :: 3 4 7 -2 2 1 4 2
     * presum :: 3 7 14 12 14 15 19 21
     * index :: 0 1 2 3 4 5 6 7
     * <p>
     * Map should look like ::
     * (0,1) , (3,1) , (14,2) , (12,1) , (15,1) , (19,1) , (21,1)
     * <p>
     * Consider 21(presum) now what we do is sum-k that is 21-7 = 14 .
     * Now we will check our map if we go by just count++ logic we will just increment the count once and here is where we go wrong.
     * <p>
     * When we search for 14 in presum array we find it on 2 and 4 index. The logic here is that 14 + 7 = 21 so the array between indexes
     * -> 3 to 7 (-2 2 1 4 2)
     * -> 5 to 7 both have sum 7 ( 1 4 2)
     * The sum is still 7 in this case because there are negative numbers that balance up for. So if we consider count++
     * we will have one count less as we will consider only array 5 to 7 but now we know that 14 sum occured earlier too
     * so even that needs to be added up so map.get(sum-k).
     * <p>
     * Another way of understanding this is that if there is increase of k in the presum array we have found a new subarray so that is why we look for currentSum-k.
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        // Edge cases
        if (nums.length == 0) return 0;

        // Sliding window -- No, contains negative number
        // hashmap + preSum
        /*
            1. Hashmap<sum[0,i - 1], frequency>
            2. sum[i, j] = sum[0, j] - sum[0, i - 1]    --> sum[0, i - 1] = sum[0, j] - sum[i, j]
                   k           sum      hashmap-key     -->  hashmap-key  =  sum - k
            3. now, we have k and sum.
                  As long as we can find a sum[0, i - 1], we then get a valid subarray
                 which is as long as we have the hashmap-key,  we then get a valid subarray
            4. Why don't map.put(sum[0, i - 1], 1) every time ?
                  if all numbers are positive, this is fine
                  if there exists negative number, there could be preSum frequency > 1
        */
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int result = 0;
        map.put(0, 1);//I see ...After spending some time on the analysis,
        // I found the reason behind having initialize preSum.put(0,1)....
        // it is for those (sum - k) == 0 calculations which are valid subarrays
        // but need to get counted. e.g. if k = 7 and sum = 7
        // (at second element for array is : 3, 4, 3, 8) at some iteration.....
        // then sum - k = 0....this 0 will get counted in statement result += preSum.get(sum - k);
        for (int cur : nums) {
            sum += cur;
            if (map.containsKey(sum - k))  // there exist a key, that [hashmap-key  =  sum - k]
                result += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
