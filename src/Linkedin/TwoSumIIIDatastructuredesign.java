package Linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * <p>
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * <p>
 * Example 1:
 * <p>
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * Example 2:
 * <p>
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 */
public class TwoSumIIIDatastructuredesign {

    /*
    If consider more find and less add or we only care time complexity in finding.For example,
    add operation can be pre-done.



    Set<Integer> sums = new HashSet<>();
    Set<Integer> nums = new HashSet<>();


    public TwoSumIIIDatastructuredesign() {

    }


    public void add(int number) {
        for (int n : nums)
            sums.add(number + n);
        nums.add(number);
    }

    public boolean find(int value) {
        return sums.contains(value);
    }
*/


    /*
    Map<Integer, Integer> map = new HashMap<>();

     Initialize your data structure here.
    public TwoSum() {

    }

    /** Add the number to an internal data structure..
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for(int key : map.keySet()) {
            int remain = value - key;
            if (remain == key && map.get(key) >= 2) return true;
            if (remain != key && map.containsKey(remain)) return true;
        }
        return false;
    }
     */

    private List<Integer> list = new ArrayList<Integer>();
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // Add the number to an internal data structure.
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
        list.add(number);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (int i = 0; i < list.size(); i++) {
            int num1 = list.get(i), num2 = value - num1;
            if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2)))
                return true;
        }
        return false;
    }

}
