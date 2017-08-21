package org.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raj on 8/20/17.
 */
public class SumMaxSubArray {

    public static int findMaxSubArray(List<Integer> A){
        int minSum = 0;
        int maxSum = 0;
        int resultSum = 0;

        for (int i=0; i < A.size(); ++i){
            resultSum += A.get(i);
            if (resultSum < minSum){
                minSum = resultSum;
            }
            if (resultSum - minSum > maxSum){
                maxSum = resultSum - minSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        List<Integer> arrSum = Arrays.asList(904,40,523,12,-335,-385,-124,481,-31);
        System.out.println("Sum is : " + findMaxSubArray(arrSum));
    }
}
