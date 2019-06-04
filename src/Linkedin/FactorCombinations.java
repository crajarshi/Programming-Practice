package Linkedin;

import java.util.LinkedList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,
 * <p>
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * <p>
 * Note:
 * <p>
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Example 1:
 * <p>
 * Input: 1
 * Output: []
 * Example 2:
 * <p>
 * Input: 37
 * Output:[]
 * Example 3:
 * <p>
 * Input: 12
 * Output:
 * [
 * [2, 6],
 * [2, 2, 3],
 * [3, 4]
 * ]
 * Example 4:
 * <p>
 * Input: 32
 * Output:
 * [
 * [2, 16],
 * [2, 2, 8],
 * [2, 2, 2, 4],
 * [2, 2, 2, 2, 2],
 * [2, 4, 4],
 * [4, 8]
 * ]
 */
public class FactorCombinations {

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (n <= 3) return ret;
        List<Integer> path = new LinkedList<Integer>();
        getFactors(2, n, path, ret);
        return ret;
    }

    private static void getFactors(int start, int n, List<Integer> path,
                                   List<List<Integer>> ret) {
        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0 && n / i >= i) {  // The previous factor is no bigger than the next
                path.add(i);
                path.add(n / i);
                ret.add(new LinkedList<Integer>(path));
                /*
                The upper bound for the factors of n is (int)sqrt(n), and
                when you find one factor of n, then put the factor and its corresponding
                factor to a temp list, and add the temp list to the result list.
                Then we remove the larger factor from the temp list,
                and recursively do the larger factor from the smaller factor
                to upper bound for the same procedure.

For example, n = 16.
Let the variable i be from 2 to 4, when i = 2, then i is one factor of 16,
and its corresponding factor is 8, so we add 2 and 8 to a temp list,
then add the temp list to the result list. And remove 8 from the temp list,
and recursively do 8 from 2 to 2 for the same procedure.

The result should be:
[2, 8]
[2, 2, 4]
[2, 2, 2, 2]
[4, 4]
                 */
                path.remove(path.size() - 1);//

                getFactors(i, n / i, path, ret);
                path.remove(path.size() - 1);//
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getFactors(16));
    }
}
