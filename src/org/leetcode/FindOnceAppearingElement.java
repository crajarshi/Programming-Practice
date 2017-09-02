package org.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Raj on 9/2/17.
 */
public class FindOnceAppearingElement {

    public static Integer findElementOnce(List<Integer> arr){

        int a = 0;
        for (Integer val : arr){
            a ^= val;
        }
        return a;
    }

    public static void main(String[] args) {
        List<Integer> lstArr = Arrays.asList(1,2,3,4,5,1,3,4,5);
        System.out.println(findElementOnce(lstArr));
    }
}
