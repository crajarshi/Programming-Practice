package org;

/**
 * Created by Raj on 7/13/17.
 */
public class BinarySearch {

    public static int binarySearchRecursive(int arr[], int high, int low, int key) {
        int mid = low + (high - low) / 2;
        if (low > high) return -1;
        if (key == arr[mid]) return mid;
        if (key < arr[mid])
            return binarySearchRecursive(arr, mid - 1, low, key);
        else
            return binarySearchRecursive(arr, high, mid + 1, key);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int key = 5;

        int result = binarySearchRecursive(arr, 6, 0, 5);

        if (result == -1)
            System.out.println(" Element Not Found");
        else
            System.out.println(" Element Found at index" + result);

    }
}
