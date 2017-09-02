package com.epi.practice.ch5.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raj on 8/16/17.
 */
public class RemoveDupSortedArray {

    public static List<Integer> deleteDup(List<Integer> A) {
//        if (A.isEmpty()) {
//            return 0;
//        }

        int writeIndex = 1;

        for (int i = 1; i < A.size(); ++i) {
            if (!A.get(writeIndex - 1).equals(A.get(i))) {
                A.set(writeIndex++, A.get(i));
            }
        }
        return A;
    }

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(4);
        lst.add(5);
        List<Integer> arr = Arrays.asList(1,2,3,4,4,5,6,6,6,7,7);

        System.out.println(deleteDup(arr));
    }
}