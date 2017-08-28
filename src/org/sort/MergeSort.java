package org.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raj on 8/27/17.
 */
public class MergeSort {

    public static List<Integer> mergeSort(List<Integer> arr) {

        int lenOfArray = arr.size();
        if (lenOfArray < 2)
            return arr;
        int mid = lenOfArray / 2;
        List<Integer> leftArr = new ArrayList<>(mid);
        List<Integer> rightArr = new ArrayList<>(lenOfArray - mid);
        for (int i = 0; i < mid - 1; ++i) {
            leftArr.add(i, arr.get(i));
        }
        for (int i = mid; i < lenOfArray - 1; ++i) {
            rightArr.add(i -mid , arr.get(i));
        }
        mergeSort(leftArr);
        mergeSort(rightArr);
        return mergeArrays(leftArr,rightArr,arr);

    }

    public static List<Integer> mergeArrays(List<Integer> left,List<Integer> right, List<Integer> mainArr){
        int i =0;
        int j =0;
        int k =0;


    }
    public static void main(String[] args) {
        List<Integer> lstArr = Arrays.asList(2, 4, 1, 6, 8, 5, 3, 7);
    }
}
