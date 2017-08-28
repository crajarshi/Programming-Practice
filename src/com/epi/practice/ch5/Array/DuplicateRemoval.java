package com.epi.practice.ch5.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raj on 8/26/17.
 */
public class DuplicateRemoval {

    public static List<Integer> removeDup(List<Integer> a){
       int count = 1;
       List<Integer> arr = new ArrayList<>();
        for (int i =1; i < a.size(); ++i){
            if (!a.get(count -1).equals(a.get(i))){
                arr.add(count -1 ,a.get(count -1));
                count++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,2,3,4,4,5,6,6,6,7,7);
        System.out.println(removeDup(arr));
    }
}
