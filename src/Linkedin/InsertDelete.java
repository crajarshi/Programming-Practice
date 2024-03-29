package Linkedin;

import java.util.*;

/**
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 */
public class InsertDelete {
    /**
     * The List is used to store numbers and serve the getRandom() method. The Map contains the mapping between the value and its index in the ArrayList.
     * The Map helps to check whether a value is already inserted or not.
     * The main trick is when you remove a value. ArrayList's remove method is O(n) if you remove from random location.
     * To overcome that, we swap the values between (randomIndex, lastIndex)
     * and always remove the entry from the end of the list. After the swap,
     * you need to update the new index of the swapped value (which was previously at the end of the list) in the map.
     */
    HashMap<Integer, Integer> valToInd;
    List<Integer> list;
    int ind = 0;

    /**
     * Initialize your data structure here.
     */
    public InsertDelete() {
        valToInd = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valToInd.containsKey(val)) return false;
        list.add(val);
        valToInd.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        int ind = valToInd.getOrDefault(val, -1);
        if (ind == -1) return false;
        Collections.swap(list, ind, list.size() - 1);
        int swappedWith = list.get(ind);
        valToInd.put(swappedWith, ind);
        list.remove(list.size() - 1);
        valToInd.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {

        return list.get( new Random().nextInt(list.size()));
    }
}
