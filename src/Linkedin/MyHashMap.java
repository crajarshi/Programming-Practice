package Linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 * <p>
 * Implement the MyHashMap class:
 * <p>
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 * <p>
 * Explanation
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 */
public class MyHashMap {
    /**
     * For simplicity, are the keys integers only?
     * For collision resolution, can we use chaining?
     * Do we have to worry about load factors?
     * Can we assume inputs are valid or do we have to validate them?
     * Can we assume this fits memory?
     */
    //Load factor = 10000/size = 0.75
    List<int[]>[] lists; //using List<List<int[]>> lists is fine as well.
    int size = 13000;

    //O(size)

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        lists = new ArrayList[size];
        for (int i = 0; i < lists.length; i++)
            lists[i] = new ArrayList<>(); //don't use linkedlist in my version which makes put() O(L^2)
    }

    public int getHashcode(int key) {
        return key % size;
    }

    //O(L)

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int index = getHashcode(key);
        for (int i = 0; i < lists[index].size(); i++) {
            if (lists[index].get(i)[0] == key) {
                lists[index].get(i)[1] = value;
                return;
            }
        }
        lists[index].add(new int[]{key, value});
    }

    //O(L)

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int index = getHashcode(key);
        for (int i = 0; i < lists[index].size(); i++) {
            if (lists[index].get(i)[0] == key)
                return lists[index].get(i)[1];
        }
        return -1;
    }

    //O(L)

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int index = getHashcode(key);
        for (int i = 0; i < lists[index].size(); i++) {
            if (lists[index].get(i)[0] == key)
                lists[index].remove(i);
        }
        return;
    }
}


/**
 * //Load factor = 10000/size = 0.75
 * List<int[]>[] lists; //using List<List<int[]>> lists is fine as well.
 * int size = 13000;
 * <p>
 * //O(size)
 * /** Initialize your data structure here.
 * <p>
 * <p>
 * public MyHashMap() {
 * lists = new ArrayList[size];
 * for (int i = 0; i < lists.length; i++)
 * lists[i] = new ArrayList<>(); //don't use linkedlist in my version which makes put() O(L^2)
 * }
 * <p>
 * <p>
 * <p>
 * public int getHashcode(int key)
 * <p>
 * <p>
 * {
 * return key % size;
 * }
 * <p>
 * //O(L)
 * value will always be non-negative.
 * <p>
 * <p>
 * public void put(int key, int value) {
 * int index = getHashcode(key);
 * for (int i = 0; i < lists[index].size(); i++)
 * {
 * if (lists[index].get(i)[0] == key)
 * {
 * lists[index].get(i)[1] = value;
 * return;
 * }
 * }
 * lists[index].add(new int[]{key, value});
 * }
 * <p>
 * //O(L)
 * /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
 * <p>
 * <p>
 * public int get(int key) {
 * int index = getHashcode(key);
 * for (int i = 0; i < lists[index].size(); i++)
 * {
 * if (lists[index].get(i)[0] == key)
 * return lists[index].get(i)[1];
 * }
 * return -1;
 * }
 * <p>
 * //O(L)
 * /** Removes the mapping of the specified value key if this map contains a mapping for the key
 * <p>
 * <p>
 * public void remove(int key) {
 * int index = getHashcode(key);
 * for (int i = 0; i < lists[index].size(); i++)
 * {
 * if (lists[index].get(i)[0] == key)
 * lists[index].remove(i);
 * }
 * return;
 * }
 */

